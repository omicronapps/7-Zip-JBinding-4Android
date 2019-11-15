package net.sf.sevenzipjbinding.junit.snippets;

import net.sf.sevenzipjbinding.junit.JUnitTestBase;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.Test;

/**
 * Tests {@link CompressGeneric} snippet.
 *
 * @author Boris Brodski
 * @since 9.20-2.00
 */
public class UpdateAlterItemsTest extends SnippetTest {
    private static final String SYSTEM_PROPERTY_TMP = JUnitTestBase.getTempDirName();
    /* BEGIN_OUTPUT(UpdateAlterItems) */
    String expected7z = "Update successful\n";
    /* END_OUTPUT */

    private String getExpectedOutput(String expected) {
        expected = expected.replace("\n", NEW_LINE);
        expected = expected.replace('/', File.separatorChar);
        return expected;
    }

    @Test
    public void testCompress7z() {
        String tmpDir = SYSTEM_PROPERTY_TMP;
        File archiveFile = new File(tmpDir, "updated-alter-items.7z");

        beginSnippetTest();
        UpdateAlterItems.main(new String[] {JUnitTestBase.getFileNameFromAssets("testdata/snippets/to-update.7z"), archiveFile.getAbsolutePath() });
        String output = endSnippetTest();
        assertEquals(getExpectedOutput(expected7z), output);
    }
}
