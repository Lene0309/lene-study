public class AFD {
    private String filename;
    private int protect_num;

    public AFD(String filename, int protect_num) {
        this.filename = filename;
        this.protect_num = protect_num;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    @Override
    public String toString() {
        return "AFD{" +
                "filename='" + filename + '\'' +
                ", protect_num=" + protect_num +
                '}';
    }

    public int getProtect_num() {
        return protect_num;
    }

    public void setProtect_num(int protect_num) {
        this.protect_num = protect_num;
    }
}
