package Beans;

public class SplitPage {

    private Integer rowCount; // listSize
    private Integer pageCount;//this.pageCount = (this.rowCount - 1)/this.pageSize + 1
    private Integer pageSize;

    public void setRowCount(Integer rowCount) {
        this.rowCount = rowCount;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public Integer getRowCount() {
        return rowCount;
    }
}
