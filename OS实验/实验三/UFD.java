public class UFD {
    private String filename;
    private long file_len;
    private int protect_num;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public long getFile_len() {
        return file_len;
    }

    public void setFile_len(long file_len) {
        this.file_len = file_len;
    }

    public int getProtect_num() {
        return protect_num;
    }

    public void setProtect_num(int protect_num) {
        this.protect_num = protect_num;
    }

    @Override
    public String toString() {
        return "UFD{" +
                "filename='" + filename + '\'' +
                ", filelength=" + file_len +
                ", protectnumber=" + protect_num +
                '}';
    }
}
