package com.csab.rxjava_sandbox.model;

import com.google.gson.annotations.Expose;

public class Currency {

    @Expose
    private String id;
    @Expose
    private String name;
    @Expose
    private String code;
    @Expose
    private String maintenance;

    public Currency() {}

    public Currency (String id, String name, String code, String maintenance) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.maintenance = maintenance;
    }

    /**
     *
     * @return
     * The id
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The code
     */
    public String getCode() {
        return code;
    }

    /**
     *
     * @param code
     * The code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     *
     * @return
     * The maintenance
     */
    public String getMaintenance() {
        return maintenance;
    }

    /**
     *
     * @param maintenance
     * The maintenance
     */
    public void setMaintenance(String maintenance) {
        this.maintenance = maintenance;
    }

}