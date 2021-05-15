# 7-Zip-JBinding-4Android
> Android Java wrapper for 7z archiver engine

## Summary
Android library version of [7-Zip-JBinding](http://sevenzipjbind.sourceforge.net/) java wrapper.

Native (JNI) cross-platform library to extract (password protected, multi-part) 7z Arj BZip2 Cab Chm Cpio Deb GZip HFS Iso Lzh Lzma Nsis Rar Rpm Split Tar Udf Wim Xar Z Zip archives and create 7z, Zip, Tar, GZip & BZip2 from Java on Android.

### Features
All features of [7-Zip-JBinding](http://sevenzipjbind.sourceforge.net/) supported:
- Very fast extraction of many archive formats out of Java
- Compress 7z, Zip, Tar, GZip, BZip2
- Extract password protected archives
- Extract splitted into volumes archives
- Extract multiple archives multithreaded
- 8599 JUnit tests
- Cross-platform

## Authors
7-Zip was created by Igor Pavlov ([7-Zip Web Site](https://www.7-zip.org/links.html)), with 7-Zip-JBinding initially designed and implemented by Boris Brodski ([7-Zip-JBinding Web Site](http://sevenzipjbind.sourceforge.net/)). 7-Zip-JBinding was adapted for Android by Fredrik Claesson.

## Usage
7-Zip-JBinding-4Android is currently not available on JCenter due to package name conflict with the 7-Zip-JBinding JAR library. However, it is possible to import the AAR library in Gradle from the JitPack repository.
1. Add the JitPack repository to project level `build.gradle` file (example: `MyAndroidProject/build.gradle`)
```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```
2. Add dependency to application level `build.gradle` file (example: `MyAndroidProject/app/build.gradle`)
```
dependencies {
    implementation 'com.github.omicronapps:7-Zip-JBinding-4Android:Release-16.02-2.02'
}
```
3. Sync project
4. SevenZip should now be possible to use by importing `net.sf.sevenzipjbinding.SevenZip`

## Examples
Examples of opening an existing archive, along with listing and extracting the contents are provided below.

Note that the `SevenZip` class provides static access and need not be instantiated. In addition, the native library will be automatically by calling any of the `openInArchive()` variants or `getSevenZipVersion()`.

### Listing versions
7-zip and 7-Zip-JBinding versions can be retrieved as follows. Note that calling `getSevenZipVersion()` will result in the native library being loaded, following which `isInitializedSuccessfully()` should return `true` if successful.
```
import android.util.Log;

import net.sf.sevenzipjbinding.SevenZip;

public class TestVersion {
    private static final String TAG = "TestVersion";

    public void testVersion() {
        SevenZip.Version version = SevenZip.getSevenZipVersion();
        Log.i(TAG, "7-zip version: " + version.major + "." + version.minor + "." + version.build + " (" + version.version + "), " + version.date + version.copyright);
        Log.i(TAG, "7-Zip-JBinding version: " + SevenZip.getSevenZipJBindingVersion());
        Log.i(TAG, "Native library initialized: " + SevenZip.isInitializedSuccessfully());
    }
}
```

### Listing archive contents
Open an existing archive with `openInArchive()`, providing an `IInStream` instance for access to the file archive, and an `IArchiveOpenCallback` instance for archive open status callbacks. Here `openInArchive()` will return an `IInArchive` instance through which the archive can be queried.
```
import android.util.Log;

import net.sf.sevenzipjbinding.ArchiveFormat;
import net.sf.sevenzipjbinding.IArchiveOpenCallback;
import net.sf.sevenzipjbinding.IInArchive;
import net.sf.sevenzipjbinding.PropID;
import net.sf.sevenzipjbinding.SevenZip;
import net.sf.sevenzipjbinding.SevenZipException;
import net.sf.sevenzipjbinding.impl.RandomAccessFileInStream;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class TestList {
    private static final String TAG = "TestList";

    public void testList(File file) {
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
            RandomAccessFileInStream inStream = new RandomAccessFileInStream(randomAccessFile);
            ArchiveOpenCallback callback = new ArchiveOpenCallback();
            IInArchive inArchive = SevenZip.openInArchive(ArchiveFormat.SEVEN_ZIP, inStream, callback);

            ArchiveFormat format = inArchive.getArchiveFormat();
            Log.i(TAG, "Archive format: " + format.getMethodName());

            int itemCount = inArchive.getNumberOfItems();
            Log.i(TAG, "Items in archive: " + itemCount);
            for (int i = 0; i < itemCount; i++) {
                Log.i(TAG, "File " + i + ": " + inArchive.getStringProperty(i, PropID.PATH) + " : " + inArchive.getStringProperty(i, PropID.SIZE));
            }

            inArchive.close();
            inStream.close();
        } catch (FileNotFoundException e) {
            Log.e(TAG, e.getMessage());
        } catch (SevenZipException e) {
            Log.e(TAG, e.getMessage());
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
        }
    }

    private class ArchiveOpenCallback implements IArchiveOpenCallback {
        @Override
        public void setTotal(Long files, Long bytes) {
            Log.i(TAG, "Archive open, total work: " + files + " files, " + bytes + " bytes");
        }

        @Override
        public void setCompleted(Long files, Long bytes) {
            Log.i(TAG, "Archive open, completed: " + files + " files, " + bytes + " bytes");
        }
    }
}
```

### File extraction, standard interface
Files can be extracted through `IInArchive.extract()` method. This requires providing an `IArchiveExtractCallback` implementation in order to receive status callbacks and for providing an `ISequentialOutStream` instance. Here the `ISequentialOutStream` will receive the extracted data through `write()` callbacks.
```
import android.util.Log;

import net.sf.sevenzipjbinding.ArchiveFormat;
import net.sf.sevenzipjbinding.ExtractAskMode;
import net.sf.sevenzipjbinding.ExtractOperationResult;
import net.sf.sevenzipjbinding.IArchiveExtractCallback;
import net.sf.sevenzipjbinding.IArchiveOpenCallback;
import net.sf.sevenzipjbinding.IInArchive;
import net.sf.sevenzipjbinding.ISequentialOutStream;
import net.sf.sevenzipjbinding.SevenZip;
import net.sf.sevenzipjbinding.SevenZipException;
import net.sf.sevenzipjbinding.impl.RandomAccessFileInStream;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class TestExtract {
    private static final String TAG = "TestExtract";

    public void testExtract(File file) {
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
            RandomAccessFileInStream inStream = new RandomAccessFileInStream(randomAccessFile);
            ArchiveOpenCallback callback = new ArchiveOpenCallback();
            IInArchive inArchive = SevenZip.openInArchive(ArchiveFormat.SEVEN_ZIP, inStream, callback);

            ArchiveExtractCallback extractCallback = new ArchiveExtractCallback();
            inArchive.extract(null, false, extractCallback);

            inArchive.close();
            inStream.close();
        } catch (FileNotFoundException e) {
            Log.e(TAG, e.getMessage());
        } catch (SevenZipException e) {
            Log.e(TAG, e.getMessage());
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
        }
    }


    private class ArchiveOpenCallback implements IArchiveOpenCallback {
        @Override
        public void setTotal(Long files, Long bytes) {
            Log.i(TAG, "Archive open, total work: " + files + " files, " + bytes + " bytes");
        }

        @Override
        public void setCompleted(Long files, Long bytes) {
            Log.i(TAG, "Archive open, completed: " + files + " files, " + bytes + " bytes");
        }
    }

    private class ArchiveExtractCallback implements IArchiveExtractCallback {
        @Override
        public ISequentialOutStream getStream(int index, ExtractAskMode extractAskMode) throws SevenZipException {
            Log.i(TAG, "Extract archive, get stream: " + index + " to: " + extractAskMode);
            SequentialOutStream stream = new SequentialOutStream();
            return stream;
        }

        @Override
        public void prepareOperation(ExtractAskMode extractAskMode) throws SevenZipException {
            Log.i(TAG, "Extract archive, prepare to: " + extractAskMode);
        }

        @Override
        public void setOperationResult(ExtractOperationResult extractOperationResult) throws SevenZipException {
            Log.i(TAG, "Extract archive, completed with: " + extractOperationResult);
            if (extractOperationResult != ExtractOperationResult.OK) {
                throw new SevenZipException(extractOperationResult.toString());
            }
        }

        @Override
        public void setTotal(long total) throws SevenZipException {
            Log.i(TAG, "Extract archive, work planned: " + total);
        }

        @Override
        public void setCompleted(long complete) throws SevenZipException {
            Log.i(TAG, "Extract archive, work completed: " + complete);
        }
    }

    private class SequentialOutStream implements ISequentialOutStream {
        @Override
        public int write(byte[] data) throws SevenZipException {
            if (data == null || data.length == 0) {
                throw new SevenZipException("null data");
            }
            Log.i(TAG, "Data to write: " + data.length);
            return data.length;
        }
    }
}
```

### Slow extraction, standard interface
Alternatively, the slow extraction interface can be used through `IInArchive.extractSlow()`, which however requires accessing each file individually.
```
import android.util.Log;

import net.sf.sevenzipjbinding.ArchiveFormat;
import net.sf.sevenzipjbinding.ExtractOperationResult;
import net.sf.sevenzipjbinding.IArchiveOpenCallback;
import net.sf.sevenzipjbinding.IInArchive;
import net.sf.sevenzipjbinding.ISequentialOutStream;
import net.sf.sevenzipjbinding.SevenZip;
import net.sf.sevenzipjbinding.SevenZipException;
import net.sf.sevenzipjbinding.impl.RandomAccessFileInStream;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class TestSlowExtract {
    private static final String TAG = "TestSlowExtract";

    public void testSlowExtract(File file) {
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
            RandomAccessFileInStream inStream = new RandomAccessFileInStream(randomAccessFile);
            ArchiveOpenCallback callback = new ArchiveOpenCallback();
            IInArchive inArchive = SevenZip.openInArchive(ArchiveFormat.SEVEN_ZIP, inStream, callback);

            int itemCount = inArchive.getNumberOfItems();
            SequentialOutStream outStream = new SequentialOutStream();
            for (int i = 0; i < itemCount; i++) {
                ExtractOperationResult result = inArchive.extractSlow(i, outStream);
                if (result != ExtractOperationResult.OK) {
                    Log.e(TAG, result.toString());
                }
            }

            inArchive.close();
            inStream.close();
        } catch (FileNotFoundException e) {
            Log.e(TAG, e.getMessage());
        } catch (SevenZipException e) {
            Log.e(TAG, e.getMessage());
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
        }
    }

    private class ArchiveOpenCallback implements IArchiveOpenCallback {
        @Override
        public void setTotal(Long files, Long bytes) {
            Log.i(TAG, "Archive open, total work: " + files + " files, " + bytes + " bytes");
        }

        @Override
        public void setCompleted(Long files, Long bytes) {
            Log.i(TAG, "Archive open, completed: " + files + " files, " + bytes + " bytes");
        }
    }

    private class SequentialOutStream implements ISequentialOutStream {
        @Override
        public int write(byte[] data) throws SevenZipException {
            if (data == null || data.length == 0) {
                throw new SevenZipException("null data");
            }
            Log.i(TAG, "Data to write: " + data.length);
            return data.length;
        }
    }
}
```

## Release Notes
Main features of 16.02-2.02 (Release, cross-platform, based on zip/p7zip 16.02)
* Bugfix #5 RandomAccessFileOutStream should implement Closeable
  - (https://github.com/borisbrodski/sevenzipjbinding/issues/5)

Main features of 16.02-2.01 (Release, extraction/compression/update, cross-platform, based on zip/p7zip 16.02)
* Bind 7-Zip 16.02, In-memory archive extraction/creation/update
* Extraction of
  - 7z, Arj, BZip2, Cab, Chm, Cpio, Ar/A/Lib/Deb, Fat, GZip, HFS, Iso,
    Lzh, Lzma, Nsis, Ntfs, Rar, Rpm, Split, Tar, Udf, Wim, Xar, Z, Zip
  - Archive format auto detection
  - Support for password protected and in volumes splitted archives
  - Full featured and simplified extraction interfaces
* Compression & update of
  - 7z, Zip, Tar, GZip, BZip2
  - Convenient archive format specific and generic compression APIs
  - Password protected archives (7z, zip) with encrypted archive headers (7z only)
* JavaDoc + Snippets (see documentation on the web: sevenzipjbind.sf.net)
* Over 8599 JUnit tests:
  - Initialization
  - All extraction methods
  - Compression
  - Unicode support

Main features of 9.20-2.00beta (Release candidate, extraction/compression/update, cross-platform, based on zip/p7zip 9.20)
* Extraction of
  - 7z, Arj, BZip2, Cab, Chm, Cpio, Deb, GZip, HFS, Iso, Lzh,
    Lzma, Nsis, Rar, Rpm, Split, Tar, Udf, Wim, Xar, Z, Zip
  - Archive format auto detection
  - Support for password protected and volumed archives
  - Simple extraction interface
* Compression & update of
  - 7z, Zip, Tar, GZip, BZip2
  - Archive format specific or generic compression API
* JavaDoc + Snippets (see documentation on the web: [sevenzipjbind.sf.net](http://sevenzipjbind.sf.net))
* 6766 JUnit tests:
  - 7z, Zip, Tar, Rar, Lzma, Iso, GZip, Cpio, BZIP2,
    Z, Arj, Lzh, Cab, Chm, Nsis, DEB, RPM, UDF

## License
[GNU Library or Lesser General Public License version 2.1 (LGPLv2)](LICENSE)
