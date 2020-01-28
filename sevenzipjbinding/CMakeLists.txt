cmake_minimum_required(VERSION 2.8.11)

project(SevenZipJBinding)
set(TARGET 7-Zip-JBinding)

set(ROOT src/main/cpp)
set(JBINDING_SRC ${ROOT}/jbinding-cpp)
set(TEST_SRC ${ROOT}/test)
set(P7ZIP_SRC ${ROOT}/p7zip)
set(SEVEN_ZIP_SRC ${P7ZIP_SRC})

set(JBINDING_CPP_FILES
        ${JBINDING_SRC}/Debug.cpp
        ${JBINDING_SRC}/JNITools.cpp
        ${JBINDING_SRC}/JavaStaticInfo.cpp
        ${JBINDING_SRC}/SevenZipJBinding.cpp
        ${JBINDING_SRC}/UniversalArchiveOpenCallback.cpp
        ${JBINDING_SRC}/UserTrace.cpp
        ${JBINDING_SRC}/CodecTools.cpp
        ${JBINDING_SRC}/JBindingTools.cpp
        ${JBINDING_SRC}/CHeadCacheInStream.cpp
        ${JBINDING_SRC}/JavaToCPP/JavaToCPPSevenZip.cpp
        ${JBINDING_SRC}/JavaToCPP/JavaToCPPInArchiveImpl.cpp
        ${JBINDING_SRC}/JavaToCPP/JavaToCPPOutArchiveImpl.cpp
        ${JBINDING_SRC}/CPPToJava/CPPToJavaArchiveExtractCallback.cpp
        ${JBINDING_SRC}/CPPToJava/CPPToJavaArchiveOpenCallback.cpp
        ${JBINDING_SRC}/CPPToJava/CPPToJavaArchiveOpenVolumeCallback.cpp
        ${JBINDING_SRC}/CPPToJava/CPPToJavaCryptoGetTextPassword.cpp
        ${JBINDING_SRC}/CPPToJava/CPPToJavaInStream.cpp
        ${JBINDING_SRC}/CPPToJava/CPPToJavaOutStream.cpp
        ${JBINDING_SRC}/CPPToJava/CPPToJavaProgress.cpp
        ${JBINDING_SRC}/CPPToJava/CPPToJavaSequentialInStream.cpp
        ${JBINDING_SRC}/CPPToJava/CPPToJavaSequentialOutStream.cpp
        ${JBINDING_SRC}/CPPToJava/CPPToJavaArchiveUpdateCallback.cpp
        )

if(CMAKE_BUILD_TYPE MATCHES Debug)
        set(JBINDING_CPP_FILES
                ${JBINDING_CPP_FILES}
                ${TEST_SRC}/CTests/JniToolsTest.cpp
                ${TEST_SRC}/CTests/JBindingTest.cpp
                ${TEST_SRC}/CTests/EnumTest.cpp
                ${TEST_SRC}/CTests/CHeadCacheInStreamTest.cpp
                )
endif()

set(SEVEN_ZIP_SOURCE_FILES
        ${SEVEN_ZIP_SRC}/CPP/Common/CommandLineParser.cpp
        ${SEVEN_ZIP_SRC}/CPP/Common/CRC.cpp
        ${SEVEN_ZIP_SRC}/CPP/Common/IntToString.cpp
        ${SEVEN_ZIP_SRC}/CPP/Common/ListFileUtils.cpp
        #${SEVEN_ZIP_SRC}/CPP/Common/StdInStream.cpp TODO check on Linux, remove it not needed
        ${SEVEN_ZIP_SRC}/CPP/Common/MyString.cpp
        ${SEVEN_ZIP_SRC}/CPP/Common/MyWindows.cpp
        ${SEVEN_ZIP_SRC}/CPP/Common/StringConvert.cpp
        ${SEVEN_ZIP_SRC}/CPP/Common/StringToInt.cpp
        ${SEVEN_ZIP_SRC}/CPP/Common/UTFConvert.cpp
        ${SEVEN_ZIP_SRC}/CPP/Common/MyVector.cpp
        ${SEVEN_ZIP_SRC}/CPP/Common/MyMap.cpp
        ${SEVEN_ZIP_SRC}/CPP/Common/MyXml.cpp
        ${SEVEN_ZIP_SRC}/CPP/Common/Wildcard.cpp

        ${SEVEN_ZIP_SRC}/CPP/Windows/FileDir.cpp
        ${SEVEN_ZIP_SRC}/CPP/Windows/FileFind.cpp
        ${SEVEN_ZIP_SRC}/CPP/Windows/FileIO.cpp
        ${SEVEN_ZIP_SRC}/CPP/Windows/FileName.cpp
        ${SEVEN_ZIP_SRC}/CPP/Windows/PropVariant.cpp
        ${SEVEN_ZIP_SRC}/CPP/Windows/PropVariantConv.cpp
        ${SEVEN_ZIP_SRC}/CPP/Windows/PropVariantUtils.cpp
        ${SEVEN_ZIP_SRC}/CPP/Windows/Synchronization.cpp
        ${SEVEN_ZIP_SRC}/CPP/Windows/System.cpp
        ${SEVEN_ZIP_SRC}/CPP/Windows/TimeUtils.cpp

        ${SEVEN_ZIP_SRC}/CPP/7zip/Common/CreateCoder.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Common/CWrappers.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Common/FilePathAutoRename.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Common/FileStreams.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Common/FilterCoder.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Common/InBuffer.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Common/InOutTempBuffer.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Common/LimitedStreams.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Common/MemBlocks.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Common/MethodId.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Common/MethodProps.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Common/OffsetStream.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Common/OutBuffer.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Common/OutMemStream.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Common/ProgressMt.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Common/ProgressUtils.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Common/PropId.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Common/StreamBinder.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Common/StreamObjects.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Common/StreamUtils.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Common/UniqBlocks.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Common/VirtThread.cpp

        ${SEVEN_ZIP_SRC}/CPP/7zip/UI/Common/LoadCodecs.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/UI/Common/PropIDUtils.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/UI/Console/ConsoleClose.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Archive/ZHandler.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Archive/Bz2Handler.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Archive/DeflateProps.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Archive/GzHandler.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Archive/FatHandler.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Archive/HandlerCont.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Archive/HfsHandler.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Archive/LzmaHandler.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Archive/NtfsHandler.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Archive/PpmdHandler.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Archive/SplitHandler.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Archive/XzHandler.cpp

        ${SEVEN_ZIP_SRC}/CPP/7zip/Archive/Common/CoderMixer2.cpp
        #${SEVEN_ZIP_SRC}/CPP/7zip/Archive/Common/CrossThreadProgress.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Archive/Common/DummyOutStream.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Archive/Common/FindSignature.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Archive/Common/HandlerOut.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Archive/Common/InStreamWithCRC.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Archive/Common/ItemNameUtils.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Archive/Common/MultiStream.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Archive/Common/OutStreamWithCRC.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Archive/Common/OutStreamWithSha1.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Archive/Common/ParseProperties.cpp

        ${SEVEN_ZIP_SRC}/CPP/7zip/Archive/7z/7zCompressionMode.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Archive/7z/7zDecode.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Archive/7z/7zEncode.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Archive/7z/7zExtract.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Archive/7z/7zFolderInStream.cpp
        #${SEVEN_ZIP_SRC}/CPP/7zip/Archive/7z/7zFolderOutStream.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Archive/7z/7zHandler.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Archive/7z/7zHandlerOut.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Archive/7z/7zHeader.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Archive/7z/7zIn.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Archive/7z/7zOut.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Archive/7z/7zProperties.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Archive/7z/7zSpecStream.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Archive/7z/7zUpdate.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Archive/7z/7zRegister.cpp

        ${SEVEN_ZIP_SRC}/CPP/7zip/Archive/Cab/CabBlockInStream.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Archive/Cab/CabHandler.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Archive/Cab/CabHeader.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Archive/Cab/CabIn.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Archive/Cab/CabRegister.cpp

        ${SEVEN_ZIP_SRC}/CPP/7zip/Archive/LzhHandler.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Compress/LzhDecoder.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Compress/LzmsDecoder.cpp

        ${SEVEN_ZIP_SRC}/CPP/7zip/Archive/Chm/ChmHandler.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Archive/Chm/ChmIn.cpp

        ${SEVEN_ZIP_SRC}/CPP/7zip/Archive/Udf/UdfHandler.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Archive/Udf/UdfIn.cpp

        ${SEVEN_ZIP_SRC}/CPP/7zip/Archive/Wim/WimHandler.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Archive/Wim/WimHandlerOut.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Archive/Wim/WimIn.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Archive/Wim/WimRegister.cpp

        # Com ??


        ${SEVEN_ZIP_SRC}/CPP/7zip/Archive/ArHandler.cpp

        ${SEVEN_ZIP_SRC}/CPP/7zip/Archive/RpmHandler.cpp

        ${SEVEN_ZIP_SRC}/CPP/7zip/Archive/XarHandler.cpp

        ${SEVEN_ZIP_SRC}/CPP/7zip/Archive/ArjHandler.cpp

        ${SEVEN_ZIP_SRC}/CPP/7zip/Archive/CpioHandler.cpp


        ${SEVEN_ZIP_SRC}/CPP/7zip/Archive/Iso/IsoHandler.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Archive/Iso/IsoHeader.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Archive/Iso/IsoIn.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Archive/Iso/IsoRegister.cpp

        ${SEVEN_ZIP_SRC}/CPP/7zip/Archive/Nsis/NsisDecode.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Archive/Nsis/NsisHandler.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Archive/Nsis/NsisIn.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Archive/Nsis/NsisRegister.cpp

        ${SEVEN_ZIP_SRC}/CPP/7zip/Archive/Rar/RarHandler.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Archive/Rar/Rar5Handler.cpp

        ${SEVEN_ZIP_SRC}/CPP/7zip/Archive/Tar/TarHandler.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Archive/Tar/TarHandlerOut.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Archive/Tar/TarHeader.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Archive/Tar/TarIn.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Archive/Tar/TarOut.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Archive/Tar/TarRegister.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Archive/Tar/TarUpdate.cpp

        # Udf

        # Wim

        ${SEVEN_ZIP_SRC}/CPP/7zip/Archive/Zip/ZipAddCommon.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Archive/Zip/ZipHandler.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Archive/Zip/ZipHandlerOut.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Archive/Zip/ZipIn.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Archive/Zip/ZipItem.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Archive/Zip/ZipOut.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Archive/Zip/ZipUpdate.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Archive/Zip/ZipRegister.cpp

        ${SEVEN_ZIP_SRC}/CPP/7zip/Compress/Bcj2Coder.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Compress/Bcj2Register.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Compress/BcjCoder.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Compress/BcjRegister.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Compress/BitlDecoder.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Compress/BranchMisc.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Compress/BranchRegister.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Compress/ByteSwap.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Compress/BZip2Crc.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Compress/BZip2Decoder.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Compress/BZip2Encoder.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Compress/BZip2Register.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Compress/CopyCoder.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Compress/CopyRegister.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Compress/Deflate64Register.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Compress/DeflateDecoder.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Compress/DeflateEncoder.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Compress/DeflateRegister.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Compress/DeltaFilter.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Compress/Lzma2Decoder.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Compress/Lzma2Encoder.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Compress/Lzma2Register.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Compress/ImplodeDecoder.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Compress/ImplodeHuffmanDecoder.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Compress/LzmaDecoder.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Compress/LzmaEncoder.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Compress/LzmaRegister.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Compress/LzOutWindow.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Compress/LzxDecoder.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Compress/PpmdDecoder.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Compress/PpmdEncoder.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Compress/PpmdRegister.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Compress/PpmdZip.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Compress/QuantumDecoder.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Compress/Rar1Decoder.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Compress/Rar2Decoder.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Compress/Rar3Decoder.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Compress/Rar3Vm.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Compress/Rar5Decoder.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Compress/RarCodecsRegister.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Compress/ShrinkDecoder.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Compress/ZDecoder.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Compress/XpressDecoder.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Compress/ZlibDecoder.cpp


        ${SEVEN_ZIP_SRC}/CPP/7zip/Crypto/7zAes.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Crypto/7zAesRegister.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Crypto/HmacSha1.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Crypto/HmacSha256.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Crypto/MyAes.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Crypto/Pbkdf2HmacSha1.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Crypto/RandGen.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Crypto/Rar20Crypto.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Crypto/Rar5Aes.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Crypto/RarAes.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Crypto/WzAes.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Crypto/ZipCrypto.cpp
        ${SEVEN_ZIP_SRC}/CPP/7zip/Crypto/ZipStrong.cpp
        ${SEVEN_ZIP_SRC}/C/Bcj2.c
        ${SEVEN_ZIP_SRC}/C/Bcj2Enc.c
        ${SEVEN_ZIP_SRC}/C/Blake2s.c
        ${SEVEN_ZIP_SRC}/C/Bra.c
        ${SEVEN_ZIP_SRC}/C/Bra86.c
        ${SEVEN_ZIP_SRC}/C/BraIA64.c
        ${SEVEN_ZIP_SRC}/C/BwtSort.c
        ${SEVEN_ZIP_SRC}/C/CpuArch.c
        ${SEVEN_ZIP_SRC}/C/HuffEnc.c
        ${SEVEN_ZIP_SRC}/C/LzFind.c
        ${SEVEN_ZIP_SRC}/C/LzFindMt.c
        ${SEVEN_ZIP_SRC}/C/LzmaDec.c
        ${SEVEN_ZIP_SRC}/C/LzmaEnc.c
        ${SEVEN_ZIP_SRC}/C/Sort.c
        ${SEVEN_ZIP_SRC}/C/Sha1.c
        ${SEVEN_ZIP_SRC}/C/Threads.c
        ${SEVEN_ZIP_SRC}/C/Aes.c
        ${SEVEN_ZIP_SRC}/C/Alloc.c
        ${SEVEN_ZIP_SRC}/C/Sha256.c
        ${SEVEN_ZIP_SRC}/C/7zCrc.c
        ${SEVEN_ZIP_SRC}/C/7zCrcOpt.c

        ${SEVEN_ZIP_SRC}/C/7zStream.c
        ${SEVEN_ZIP_SRC}/C/Delta.c
        ${SEVEN_ZIP_SRC}/C/Lzma2Dec.c
        ${SEVEN_ZIP_SRC}/C/Lzma2Enc.c
        ${SEVEN_ZIP_SRC}/C/MtCoder.c
        ${SEVEN_ZIP_SRC}/C/Ppmd7.c
        ${SEVEN_ZIP_SRC}/C/Ppmd7Dec.c
        ${SEVEN_ZIP_SRC}/C/Ppmd7Enc.c
        ${SEVEN_ZIP_SRC}/C/Ppmd8.c
        ${SEVEN_ZIP_SRC}/C/Ppmd8Dec.c
        ${SEVEN_ZIP_SRC}/C/Ppmd8Enc.c
        ${SEVEN_ZIP_SRC}/C/Xz.c
        ${SEVEN_ZIP_SRC}/C/XzCrc64.c
        ${SEVEN_ZIP_SRC}/C/XzCrc64Opt.c
        ${SEVEN_ZIP_SRC}/C/XzDec.c
        ${SEVEN_ZIP_SRC}/C/XzEnc.c
        ${SEVEN_ZIP_SRC}/C/XzIn.c
        )

set(SEVEN_ZIP_PLATFORM_SOURCE_FILES
        ${SEVEN_ZIP_SRC}/CPP/myWindows/wine_date_and_time.cpp
        )

set(JBINDING_JAVAH_H_FILES
        ${JBINDING_SRC}/javah/net_sf_sevenzipjbinding_impl_InArchiveImpl.h
        ${JBINDING_SRC}/javah/net_sf_sevenzipjbinding_SevenZip.h
        )

add_definitions(
        -fmessage-length=0
        -O2
        )

add_definitions(
        -DBREAK_HANDLER
        -DENV_HAVE_GCCVISIBILITYPATCH
        -DENV_UNIX
        -DUNICODE
        -DUNIX_USE_WIN_FILE
        -D_7_Zip_JBinding_EXPORTS
        -D_FILE_OFFSET_BITS=64
        -D_LARGEFILE_SOURCE
        -D_REENTRANT
        -D_UNICODE
        )

if(CMAKE_BUILD_TYPE MATCHES Debug)
        add_definitions(-D_DEBUG)
elseif(CMAKE_BUILD_TYPE MATCHES Release)
        add_definitions(-DNDEBUG)
endif()

include_directories(${P7ZIP_SRC})
include_directories(${P7ZIP_SRC}/CPP)
include_directories(${P7ZIP_SRC}/C)
include_directories(${P7ZIP_SRC}/CPP/myWindows)
include_directories(${P7ZIP_SRC}/CPP/include_windows)
include_directories(${JBINDING_SRC}/javah)
include_directories(${JBINDING_SRC})

set(CMAKE_C_FLAGS "${CMAKE_C_FLAGS} -fmessage-length=0 -Wno-narrowing")
set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -fmessage-length=0 -Wno-narrowing")

add_library(${TARGET} SHARED
        ${JBINDING_CPP_FILES}
        ${JBINDING_PLATFORM_CPP_FILES}
        ${SEVEN_ZIP_SOURCE_FILES}
        ${SEVEN_ZIP_PLATFORM_SOURCE_FILES}
        ${JBINDING_JAVAH_H_FILES}
        )
find_library(LOG-LIB
        log)
set_target_properties(${TARGET} PROPERTIES LINK_FLAGS "-Wl,-z,defs -Wl,--no-undefined")
target_link_libraries(${TARGET}
        ${LOG-LIB})
