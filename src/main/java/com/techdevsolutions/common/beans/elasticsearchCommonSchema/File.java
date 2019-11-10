package com.techdevsolutions.common.beans.elasticsearchCommonSchema;

import java.util.Date;
import java.util.Objects;

public class File extends Base {
    private Date accessed;
    private Date created;
    private Date ctime;
    private String device;
    private String directory;
    private String extension;
    private String gid;
    private String group;
    private String md5;
    private String sha1;
    private String sha256;
    private String sha512;
    private String inode;
    private String mode;
    private Date mtime;
    private String name;
    private String owner;
    private String path;
    private Long size;
    private String targetPath;
    private String type;
    private String uid;

    @Override
    public String toString() {
        return "File{" +
                "accessed=" + accessed +
                ", created=" + created +
                ", ctime=" + ctime +
                ", device='" + device + '\'' +
                ", directory='" + directory + '\'' +
                ", extension='" + extension + '\'' +
                ", gid='" + gid + '\'' +
                ", group='" + group + '\'' +
                ", md5='" + md5 + '\'' +
                ", sha1='" + sha1 + '\'' +
                ", sha256='" + sha256 + '\'' +
                ", sha512='" + sha512 + '\'' +
                ", inode='" + inode + '\'' +
                ", mode='" + mode + '\'' +
                ", mtime=" + mtime +
                ", name='" + name + '\'' +
                ", owner='" + owner + '\'' +
                ", path='" + path + '\'' +
                ", size=" + size +
                ", targetPath='" + targetPath + '\'' +
                ", type='" + type + '\'' +
                ", uid='" + uid + '\'' +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof File)) return false;
        if (!super.equals(o)) return false;
        File file = (File) o;
        return Objects.equals(accessed, file.accessed) &&
                Objects.equals(created, file.created) &&
                Objects.equals(ctime, file.ctime) &&
                Objects.equals(device, file.device) &&
                Objects.equals(directory, file.directory) &&
                Objects.equals(extension, file.extension) &&
                Objects.equals(gid, file.gid) &&
                Objects.equals(group, file.group) &&
                Objects.equals(md5, file.md5) &&
                Objects.equals(sha1, file.sha1) &&
                Objects.equals(sha256, file.sha256) &&
                Objects.equals(sha512, file.sha512) &&
                Objects.equals(inode, file.inode) &&
                Objects.equals(mode, file.mode) &&
                Objects.equals(mtime, file.mtime) &&
                Objects.equals(name, file.name) &&
                Objects.equals(owner, file.owner) &&
                Objects.equals(path, file.path) &&
                Objects.equals(size, file.size) &&
                Objects.equals(targetPath, file.targetPath) &&
                Objects.equals(type, file.type) &&
                Objects.equals(uid, file.uid);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), accessed, created, ctime, device, directory, extension, gid, group, md5, sha1, sha256, sha512, inode, mode, mtime, name, owner, path, size, targetPath, type, uid);
    }

    public Date getAccessed() {
        return accessed;
    }

    public File setAccessed(Date accessed) {
        this.accessed = accessed;
        return this;
    }

    public Date getCreated() {
        return created;
    }

    public File setCreated(Date created) {
        this.created = created;
        return this;
    }

    public Date getCtime() {
        return ctime;
    }

    public File setCtime(Date ctime) {
        this.ctime = ctime;
        return this;
    }

    public String getDevice() {
        return device;
    }

    public File setDevice(String device) {
        this.device = device;
        return this;
    }

    public String getDirectory() {
        return directory;
    }

    public File setDirectory(String directory) {
        this.directory = directory;
        return this;
    }

    public String getExtension() {
        return extension;
    }

    public File setExtension(String extension) {
        this.extension = extension;
        return this;
    }

    public String getGid() {
        return gid;
    }

    public File setGid(String gid) {
        this.gid = gid;
        return this;
    }

    public String getGroup() {
        return group;
    }

    public File setGroup(String group) {
        this.group = group;
        return this;
    }

    public String getMd5() {
        return md5;
    }

    public File setMd5(String md5) {
        this.md5 = md5;
        return this;
    }

    public String getSha1() {
        return sha1;
    }

    public File setSha1(String sha1) {
        this.sha1 = sha1;
        return this;
    }

    public String getSha256() {
        return sha256;
    }

    public File setSha256(String sha256) {
        this.sha256 = sha256;
        return this;
    }

    public String getSha512() {
        return sha512;
    }

    public File setSha512(String sha512) {
        this.sha512 = sha512;
        return this;
    }

    public String getInode() {
        return inode;
    }

    public File setInode(String inode) {
        this.inode = inode;
        return this;
    }

    public String getMode() {
        return mode;
    }

    public File setMode(String mode) {
        this.mode = mode;
        return this;
    }

    public Date getMtime() {
        return mtime;
    }

    public File setMtime(Date mtime) {
        this.mtime = mtime;
        return this;
    }

    public String getName() {
        return name;
    }

    public File setName(String name) {
        this.name = name;
        return this;
    }

    public String getOwner() {
        return owner;
    }

    public File setOwner(String owner) {
        this.owner = owner;
        return this;
    }

    public String getPath() {
        return path;
    }

    public File setPath(String path) {
        this.path = path;
        return this;
    }

    public Long getSize() {
        return size;
    }

    public File setSize(Long size) {
        this.size = size;
        return this;
    }

    public String getTargetPath() {
        return targetPath;
    }

    public File setTargetPath(String targetPath) {
        this.targetPath = targetPath;
        return this;
    }

    public String getType() {
        return type;
    }

    public File setType(String type) {
        this.type = type;
        return this;
    }

    public String getUid() {
        return uid;
    }

    public File setUid(String uid) {
        this.uid = uid;
        return this;
    }
}
