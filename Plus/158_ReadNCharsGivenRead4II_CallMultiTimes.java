/*
158 Read N Characters Given Read4 II - Call multiple times

The API: int read4(char *buf) reads 4 characters at a time from a file.

The return value is the actual number of characters read. For example, it returns 3 if there is only 3 characters left in the file.

By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.

Note:
The read function may be called multiple times.

Subscribe to see which companies asked this question
*/

/* The read4 API is defined in the parent class Reader4.
int read4(char[] buf); */
public class Solution extends Reader4 {
    private char[] buffer = new char[4];
    int offset = 0; // where to start copying
    int bufsize = 0; // partially invalid [offset, offset + bufsize)
    /**
    * @param buf Destination buffer
    * @param n Maximum number of characters to read
    * @return The number of characters read
    */
    public int read(char[] buf, int n) {
        int readBytes = 0;
        boolean eof = false;
        while (!eof && readBytes < n) {
            if (bufsize == 0) {
                bufsize = read4(buffer);
                eof = bufsize < 4;
            }
            int bytes = Math.min(n - readBytes, bufsize);
            System.arraycopy(buffer /* src */, offset /* srcPos */,
                buf /* dest */, readBytes /* destPos */, bytes /* length */);
            /**/ offset = (offset + bytes) % 4; // %4: 4->0.
            bufsize -= bytes;
            readBytes += bytes;
        }
        return readBytes;
    }
}