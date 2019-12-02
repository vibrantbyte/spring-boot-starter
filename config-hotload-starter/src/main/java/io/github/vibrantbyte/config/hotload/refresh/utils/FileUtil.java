package io.github.vibrantbyte.config.hotload.refresh.utils;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.*;
import java.nio.channels.FileChannel;
import java.util.Map;

/**
 *
 * @author vibrant byte
 * @date 2017/6/13
 */
public class FileUtil {

    /**
     * 文件支持的后缀名map
     */
    private static final Map<String, FileExtension> fileExtensions = FileExtension.getSupportExtensions();

    /**
     * 文件扩展名分隔符
     */
    public static final char EXTENSION_SEPARATOR = '.';

    /**
     * The Unix separator character.
     */
    private static final char UNIX_SEPARATOR = '/';

    /**
     * The Windows separator character.
     */
    private static final char WINDOWS_SEPARATOR = '\\';

    /**
     * 文件转换为字节数组
     *
     * @param file
     * @return
     * @throws IOException
     */
    public static byte[] fileToBytes(File file) throws IOException {
        int size = (int) file.length();
        FileInputStream fin = null;
        try {
            fin = new FileInputStream(file);
            byte[] bytes = new byte[size];
            fin.read(bytes);
            return bytes;
        } catch (Exception e) {
            if (e instanceof IOException) {
                throw (IOException) e;
            } else {
                e.printStackTrace();
                return null;
            }
        } finally {
            if (fin != null) {
                try {
                    fin.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 字节数组转换为文件
     *
     * @param bytes
     * @param file
     * @throws IOException
     */
    public static void bytesToFile(byte[] bytes, File file) throws IOException {
        FileOutputStream fout = null;
        try {
            fout = new FileOutputStream(file);
            fout.write(bytes);
        } catch (Exception e) {
            if (e instanceof IOException) {
                throw (IOException) e;
            } else {
                e.printStackTrace();
            }
        } finally {
            if (fout != null) {
                try {
                    fout.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 拷贝文件
     *
     * @param src
     * @param target
     * @throws IOException
     */
    public static void copyFile(File src, File target) throws IOException {
        try (FileChannel in = new FileInputStream(src).getChannel(); FileChannel out = new FileOutputStream(target).getChannel();) {
            out.transferFrom(in, 0, src.length());
        } catch (IOException e) {
            throw e;
        }
    }

    /**
     * 文件名是否合法
     *
     * @param name
     * @return
     */
    public static boolean checkFileName(String name) {
        char[] cs = name.toCharArray();
        for (char c : cs) {
            switch (c) {
                case '\\':
                case '/':
                case '?':
                case '*':
                case '"':
                case '<':
                case '>':
                case ':':
                case '|':
                    return false;
            }
        }
        return true;
    }


    /**
     * 获取文件扩展名
     *
     * @param filename
     * @return
     */
    public static FileExtension getExtension(String filename) {
        int index = indexOfExtension(filename);
        if (index >= 0) {
            String extension = filename.substring(index + 1);
            if (extension.equals("")) {
                return FileExtension.NO_EXTENSION_FILE;
            } else if (fileExtensions.containsKey(extension)) {
                return fileExtensions.get(extension);
            } else {
                return FileExtension.NO_IDENTIFY_EXTENSION_FILE;
            }
        }
        return FileExtension.NO_EXTENSION_FILE;
    }

    public static int indexOfLastSeparator(String filename) {
        if (filename == null) {
            return -1;
        }
        int lastUnixPos = filename.lastIndexOf(UNIX_SEPARATOR);
        int lastWindowsPos = filename.lastIndexOf(WINDOWS_SEPARATOR);
        return Math.max(lastUnixPos, lastWindowsPos);
    }

    public static int indexOfExtension(String filename) {
        if (filename == null) {
            return -1;
        }
        int extensionPos = filename.lastIndexOf(EXTENSION_SEPARATOR);
        int lastSeparator = indexOfLastSeparator(filename);
        return (lastSeparator > extensionPos ? -1 : extensionPos);
    }


    /**
     * 校验xml文件是否是合法的xml
     *
     * @param file
     * @return
     */
    public static boolean isValidXml(File file) {
        /**
         * 文件对象为空或者文件不存在则认为是不合法的xml文件
         */
        if (null == file || !file.exists())
            return false;

        boolean isValid = false;
        try (InputStream in = new FileInputStream(file)) {

            XMLReader reader = XMLReaderFactory.createXMLReader();
            InputSource is = new InputSource(in);
            is.setSystemId(file.getAbsolutePath());
            reader.parse(is);
            isValid = true;
        } catch (Exception e) {
            isValid = false;
            e.printStackTrace();
        }
        return isValid;

    }

    /**
     * Removes the extension from a filename.
     * <p>
     * This method returns the textual part of the filename before the last dot.
     * There must be no directory separator after the dot.
     * <pre>
     * foo.txt    --> foo
     * a\b\c.jpg  --> a\b\c
     * a\b\c      --> a\b\c
     * a.b\c      --> a.b\c
     * </pre>
     * <p>
     * The output will be the same irrespective of the machine that the code is running on.
     *
     * @param filename the filename to query, null returns null
     * @return the filename minus the extension
     */
    public static String removeExtension(String filename) {
        if (filename == null) {
            return null;
        }
        int index = indexOfExtension(filename);
        if (index == -1) {
            return filename;
        } else {
            return filename.substring(0, index);
        }
    }

    /**
     * Gets the name minus the path from a full filename.
     * <p>
     * This method will handle a file in either Unix or Windows format.
     * The text after the last forward or backslash is returned.
     * <pre>
     * a/b/c.txt --> c.txt
     * a.txt     --> a.txt
     * a/b/c     --> c
     * a/b/c/    --> ""
     * </pre>
     * <p>
     * The output will be the same irrespective of the machine that the code is running on.
     *
     * @param filename the filename to query, null returns null
     * @return the name of the file without the path, or an empty string if none exists
     */
    public static String getName(String filename) {
        if (filename == null) {
            return null;
        }
        int index = indexOfLastSeparator(filename);
        return filename.substring(index + 1);
    }

    /**
     * Gets the base name, minus the full path and extension, from a full filename.
     * <p>
     * This method will handle a file in either Unix or Windows format.
     * The text after the last forward or backslash and before the last dot is returned.
     * <pre>
     * a/b/c.txt --> c
     * a.txt     --> a
     * a/b/c     --> c
     * a/b/c/    --> ""
     * </pre>
     * <p>
     * The output will be the same irrespective of the machine that the code is running on.
     *
     * @param filename the filename to query, null returns null
     * @return the name of the file without the path, or an empty string if none exists
     */
    public static String getBaseName(String filename) {
        return removeExtension(getName(filename));
    }
}
