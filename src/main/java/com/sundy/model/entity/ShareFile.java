package com.sundy.model.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_share_file")
public class ShareFile {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 文件的上传者
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 文件名称
     */
    @Column(name = "file_name")
    private String fileName;

    /**
     * 文件存在服务器的路径
     */
    @Column(name = "file_path")
    private String filePath;

    /**
     * 文件上传的时间
     */
    @Column(name = "file_time")
    private Date fileTime;

    /**
     * 文件的大小
     */
    @Column(name = "file_size")
    private Long fileSize;

    /**
     * 文件下载次数
     */
    @Column(name = "download_times")
    private Integer downloadTimes;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取文件的上传者
     *
     * @return user_id - 文件的上传者
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置文件的上传者
     *
     * @param userId 文件的上传者
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取文件名称
     *
     * @return file_name - 文件名称
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * 设置文件名称
     *
     * @param fileName 文件名称
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * 获取文件存在服务器的路径
     *
     * @return file_path - 文件存在服务器的路径
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * 设置文件存在服务器的路径
     *
     * @param filePath 文件存在服务器的路径
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * 获取文件上传的时间
     *
     * @return file_time - 文件上传的时间
     */
    public Date getFileTime() {
        return fileTime;
    }

    /**
     * 设置文件上传的时间
     *
     * @param fileTime 文件上传的时间
     */
    public void setFileTime(Date fileTime) {
        this.fileTime = fileTime;
    }

    /**
     * 获取文件的大小
     *
     * @return file_size - 文件的大小
     */
    public Long getFileSize() {
        return fileSize;
    }

    /**
     * 设置文件的大小
     *
     * @param fileSize 文件的大小
     */
    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    /**
     * 获取文件下载次数
     *
     * @return download_times - 文件下载次数
     */
    public Integer getDownloadTimes() {
        return downloadTimes;
    }

    /**
     * 设置文件下载次数
     *
     * @param downloadTimes 文件下载次数
     */
    public void setDownloadTimes(Integer downloadTimes) {
        this.downloadTimes = downloadTimes;
    }
}