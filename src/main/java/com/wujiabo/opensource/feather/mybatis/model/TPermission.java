package com.wujiabo.opensource.feather.mybatis.model;

public class TPermission {
    private Integer permissionId;

    private Integer permissionPid;

    private String permissionCode;

    private String permissionName;

    private String state;

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    public Integer getPermissionPid() {
        return permissionPid;
    }

    public void setPermissionPid(Integer permissionPid) {
        this.permissionPid = permissionPid;
    }

    public String getPermissionCode() {
        return permissionCode;
    }

    public void setPermissionCode(String permissionCode) {
        this.permissionCode = permissionCode == null ? null : permissionCode.trim();
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName == null ? null : permissionName.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }
}