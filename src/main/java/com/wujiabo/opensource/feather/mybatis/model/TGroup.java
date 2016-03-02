package com.wujiabo.opensource.feather.mybatis.model;

public class TGroup {
    private Integer groupId;

    private Integer groupPid;

    private String groupCode;

    private String groupName;

    private String state;

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getGroupPid() {
        return groupPid;
    }

    public void setGroupPid(Integer groupPid) {
        this.groupPid = groupPid;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode == null ? null : groupCode.trim();
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName == null ? null : groupName.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }
}