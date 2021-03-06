package cn.edu.scnu.dtindex.model;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class IndexRecord implements WritableComparable<IndexRecord> {
    private String lobid;
    private ValidTime maxNode;
    private ValidTime minNode;
    private Long lob_offset;
    private String diskFileName;//仅仅在查询时候用来保持路径名称的传递，不需要序列化


	public IndexRecord() {
	}

	public IndexRecord(String lobid, ValidTime maxNode, ValidTime minNode, Long lob_offset) {
		this.lobid = lobid;
		this.maxNode = maxNode;
		this.minNode = minNode;
		this.lob_offset = lob_offset;
		this.diskFileName = "";
	}

	@Override
    public int compareTo(IndexRecord o) {//索引记录的排序
        return this.maxNode.compareTo(o.maxNode);
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeUTF(lobid);
        maxNode.write(out);
        minNode.write(out);
        out.writeLong(lob_offset);
        out.writeUTF(diskFileName);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        this.lobid = in.readUTF();
        ValidTime max = new ValidTime();
        max.readFields(in);
        this.maxNode =max;
        ValidTime min = new ValidTime();
        min.readFields(in);
        this.minNode = min;
        this.lob_offset = in.readLong();
        this.diskFileName = in.readUTF();
    }

    @Override
    public String toString() {
        return "IndexRecord{" +
                "lobid='" + lobid + '\'' +
                ", maxNode=" + maxNode +
                ", minNode=" + minNode +
                ", lob_offset=" + lob_offset +
                '}';
    }


	public String getDiskFileName() {
		return diskFileName;
	}

	public void setDiskFileName(String diskFileName) {
		this.diskFileName = diskFileName;
	}

	public String getLobid() {
		return lobid;
	}

	public void setLobid(String lobid) {
		this.lobid = lobid;
	}

	public ValidTime getMaxNode() {
		return maxNode;
	}

	public void setMaxNode(ValidTime maxNode) {
		this.maxNode = maxNode;
	}

	public ValidTime getMinNode() {
		return minNode;
	}

	public void setMinNode(ValidTime minNode) {
		this.minNode = minNode;
	}

	public Long getLob_offset() {
		return lob_offset;
	}

	public void setLob_offset(Long lob_offset) {
		this.lob_offset = lob_offset;
	}
}
