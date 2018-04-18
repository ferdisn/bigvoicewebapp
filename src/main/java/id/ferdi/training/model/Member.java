package id.ferdi.training.model;

import java.io.InputStream;

public class Member {
    private Integer id;
    private String name;
    private InputStream file = null;
    private String filename;

    public void setId(Integer prmId) { id = prmId; }

    public void setName(String prmName) { name = prmName; }

    public void setFile(InputStream prmFile) { file = prmFile;  }

    public void setFilename(String prmName) { filename = prmName; }

    public String getName() { return name; }

    public Integer getId() { return id; }

    public InputStream getFile() { return file; }

    public String getFilename() { return filename; }

}
