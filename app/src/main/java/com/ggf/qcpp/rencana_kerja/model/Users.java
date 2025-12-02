package com.ggf.qcpp.rencana_kerja.model;

import com.google.gson.annotations.SerializedName;

public class Users {
    @SerializedName("id")
    public String id;
    @SerializedName("name")
    public String name;
    @SerializedName("pg")
    public String pg;
    @SerializedName("role")
    public String role;
    @SerializedName("email")
    public String email;

    @SerializedName("id_role")
    public String id_role;

    @SerializedName("id_unit_kerja")
    public String id_unit_kerja;

    @SerializedName("tanggal_lahir")
    public String tanggal_lahir;

    public String getPg() {
        return pg;
    }

    public void setPg(String pg) {
        this.pg = pg;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId_role() {
        return id_role;
    }

    public void setId_role(String id_role) {
        this.id_role = id_role;
    }

    public String getId_unit_kerja() {
        return id_unit_kerja;
    }

    public void setId_unit_kerja(String id_unit_kerja) {
        this.id_unit_kerja = id_unit_kerja;
    }

    public String getTanggal_lahir() {
        return tanggal_lahir;
    }

    public void setTanggal_lahir(String tanggal_lahir) {
        this.tanggal_lahir = tanggal_lahir;
    }
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
