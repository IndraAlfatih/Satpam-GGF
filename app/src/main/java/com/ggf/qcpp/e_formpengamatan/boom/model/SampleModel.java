package com.ggf.qcpp.e_formpengamatan.boom.model;

import com.google.gson.annotations.SerializedName;

public class SampleModel {

    @SerializedName("no_spk")
    public String no_spk;
    @SerializedName("lokasi")
    public String lokasi;
    @SerializedName("plot")
    public int PLOT;
    @SerializedName("no_sample") //pengisian ke
    public int no_sample;
    @SerializedName("keterangan")
    public String keterangan;

    //batas------------------------------------------

    @SerializedName("tanggal_pengamatan")
    private String tanggalPengamatan;

    @SerializedName("tanggal_aplikasi")
    private String tanggalAplikasi;

    @SerializedName("shift")
    private String shift;

    @SerializedName("mandor_bibit")
    private String mandorBibit;

    @SerializedName("kode_cameco")
    private String kodeCameco;

    @SerializedName("kode_tangki")
    private String kodeTangki;

    @SerializedName("jenis_aplikasi")
    private String jenisAplikasi;

    @SerializedName("update_peta")
    private String updatePeta;

    @SerializedName("mulai_pengisian")
    private String mulaiPengisian;

    @SerializedName("selesai_pengisian")
    private String selesaiPengisian;

    @SerializedName("total_pengisian")
    private int totalPengisian;

    @SerializedName("kode_plot_teraplikasi")
    private String kodePlotTeraplikasi;

    @SerializedName("luas_aktif")
    private double luasAktif;

    @SerializedName("luas_bruto")
    private double luas_bruto;

    @SerializedName("luas_aktif_teraplikasi")
    private double luasAktifTeraplikasi;

    @SerializedName("mulai_aplikasi")
    private String mulaiAplikasi;

    @SerializedName("selesai_aplikasi")
    private String selesaiAplikasi;

    @SerializedName("total_aplikasi")
    private int totalAplikasi;

    @SerializedName("volume_air")
    private double volumeAir;

    @SerializedName("rencana")
    private double rencana;

    @SerializedName("real")
    private double real;

    @SerializedName("no_nozzle")
    private String noNozzle;

    @SerializedName("temuan_nozzle")
    private String temuanNozzle;

    @SerializedName("berhenti_luar_plot")
    private String berhentiLuarPlot; // diubah ke String

    @SerializedName("berhenti_dalam_plot")
    private String berhentiDalamPlot; // diubah ke String

    @SerializedName("no_plot_sayap_kanan_cameco")
    private Integer noPlotSayapKananCameco;

    @SerializedName("no_plot_sayap_kiri_cameco")
    private Integer noPlotSayapKiriCameco;

    @SerializedName("kebocoran_gold_pump_bsc")
    private String kebocoranGoldPumpBsc;

    @SerializedName("kebocoran_gold_pump_tsm")
    private String kebocoranGoldPumpTsm;

    @SerializedName("kebocoran_tangki_tsm")
    private String kebocoranTangkiTsm;

    @SerializedName("pressure")
    private double pressure;

    @SerializedName("speed")
    private double speed;

    @SerializedName("suhu_saat_forcing")
    private double suhuSaatForcing;

    @SerializedName("ceklist_keaktifan_agitator_cameco")
    private String ceklistKeaktifanAgitatorCameco;

    @SerializedName("ceklist_keaktifan_agitator_tangki_suplay")
    private String ceklistKeaktifanAgitatorTangkiSuplay;

    @SerializedName("verify_mandor")
    private String verifyMandor; // diubah ke String

    @SerializedName("verify_kasi")
    private String verifyKasi; // diubah ke String

    @SerializedName("verify_kabag")
    private String verifyKabag; // diubah ke String

    @SerializedName("username")
    public String USERNAME;

    //batas------------------------------------------


    public double getLuas_bruto() {
        return luas_bruto;
    }

    public void setLuas_bruto(double luas_bruto) {
        this.luas_bruto = luas_bruto;
    }

    public String getNo_spk() {
        return no_spk;
    }

    public void setNo_spk(String no_spk) {
        this.no_spk = no_spk;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public int getPLOT() {
        return PLOT;
    }

    public void setPLOT(int PLOT) {
        this.PLOT = PLOT;
    }

    public int getNo_sample() {
        return no_sample;
    }

    public void setNo_sample(String no_sample) {
        this.no_sample = Integer.parseInt(no_sample);
    }


    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public void setNo_sample(int no_sample) {
        this.no_sample = no_sample;
    }

    public String getTanggalPengamatan() {
        return tanggalPengamatan;
    }

    public void setTanggalPengamatan(String tanggalPengamatan) {
        this.tanggalPengamatan = tanggalPengamatan;
    }

    public String getTanggalAplikasi() {
        return tanggalAplikasi;
    }

    public void setTanggalAplikasi(String tanggalAplikasi) {
        this.tanggalAplikasi = tanggalAplikasi;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public String getMandorBibit() {
        return mandorBibit;
    }

    public void setMandorBibit(String mandorBibit) {
        this.mandorBibit = mandorBibit;
    }

    public String getKodeCameco() {
        return kodeCameco;
    }

    public void setKodeCameco(String kodeCameco) {
        this.kodeCameco = kodeCameco;
    }

    public String getKodeTangki() {
        return kodeTangki;
    }

    public void setKodeTangki(String kodeTangki) {
        this.kodeTangki = kodeTangki;
    }

    public String getJenisAplikasi() {
        return jenisAplikasi;
    }

    public void setJenisAplikasi(String jenisAplikasi) {
        this.jenisAplikasi = jenisAplikasi;
    }

    public String getUpdatePeta() {
        return updatePeta;
    }

    public void setUpdatePeta(String updatePeta) {
        this.updatePeta = updatePeta;
    }

    public String getMulaiPengisian() {
        return mulaiPengisian;
    }

    public void setMulaiPengisian(String mulaiPengisian) {
        this.mulaiPengisian = mulaiPengisian;
    }

    public String getSelesaiPengisian() {
        return selesaiPengisian;
    }

    public void setSelesaiPengisian(String selesaiPengisian) {
        this.selesaiPengisian = selesaiPengisian;
    }

    public int getTotalPengisian() {
        return totalPengisian;
    }

    public void setTotalPengisian(int totalPengisian) {
        this.totalPengisian = totalPengisian;
    }

    public String getKodePlotTeraplikasi() {
        return kodePlotTeraplikasi;
    }

    public void setKodePlotTeraplikasi(String kodePlotTeraplikasi) {
        this.kodePlotTeraplikasi = kodePlotTeraplikasi;
    }

    public double getLuasAktif() {
        return luasAktif;
    }

    public void setLuasAktif(double luasAktif) {
        this.luasAktif = luasAktif;
    }

    public double getLuasAktifTeraplikasi() {
        return luasAktifTeraplikasi;
    }

    public void setLuasAktifTeraplikasi(double luasAktifTeraplikasi) {
        this.luasAktifTeraplikasi = luasAktifTeraplikasi;
    }

    public String getMulaiAplikasi() {
        return mulaiAplikasi;
    }

    public void setMulaiAplikasi(String mulaiAplikasi) {
        this.mulaiAplikasi = mulaiAplikasi;
    }

    public String getSelesaiAplikasi() {
        return selesaiAplikasi;
    }

    public void setSelesaiAplikasi(String selesaiAplikasi) {
        this.selesaiAplikasi = selesaiAplikasi;
    }

    public int getTotalAplikasi() {
        return totalAplikasi;
    }

    public void setTotalAplikasi(int totalAplikasi) {
        this.totalAplikasi = totalAplikasi;
    }

    public double getVolumeAir() {
        return volumeAir;
    }

    public void setVolumeAir(double volumeAir) {
        this.volumeAir = volumeAir;
    }

    public double getRencana() {
        return rencana;
    }

    public void setRencana(double rencana) {
        this.rencana = rencana;
    }

    public double getReal() {
        return real;
    }

    public void setReal(double real) {
        this.real = real;
    }

    public String getNoNozzle() {
        return noNozzle;
    }

    public void setNoNozzle(String noNozzle) {
        this.noNozzle = noNozzle;
    }

    public String getTemuanNozzle() {
        return temuanNozzle;
    }

    public void setTemuanNozzle(String temuanNozzle) {
        this.temuanNozzle = temuanNozzle;
    }

    public String getBerhentiLuarPlot() {
        return berhentiLuarPlot;
    }

    public void setBerhentiLuarPlot(String berhentiLuarPlot) {
        this.berhentiLuarPlot = berhentiLuarPlot;
    }

    public String getBerhentiDalamPlot() {
        return berhentiDalamPlot;
    }

    public void setBerhentiDalamPlot(String berhentiDalamPlot) {
        this.berhentiDalamPlot = berhentiDalamPlot;
    }

    public Integer getNoPlotSayapKananCameco() {
        return noPlotSayapKananCameco;
    }

    public void setNoPlotSayapKananCameco(Integer noPlotSayapKananCameco) {
        this.noPlotSayapKananCameco = noPlotSayapKananCameco;
    }

    public Integer getNoPlotSayapKiriCameco() {
        return noPlotSayapKiriCameco;
    }

    public void setNoPlotSayapKiriCameco(Integer noPlotSayapKiriCameco) {
        this.noPlotSayapKiriCameco = noPlotSayapKiriCameco;
    }

    public String getKebocoranGoldPumpBsc() {
        return kebocoranGoldPumpBsc;
    }

    public void setKebocoranGoldPumpBsc(String kebocoranGoldPumpBsc) {
        this.kebocoranGoldPumpBsc = kebocoranGoldPumpBsc;
    }

    public String getKebocoranGoldPumpTsm() {
        return kebocoranGoldPumpTsm;
    }

    public void setKebocoranGoldPumpTsm(String kebocoranGoldPumpTsm) {
        this.kebocoranGoldPumpTsm = kebocoranGoldPumpTsm;
    }

    public String getKebocoranTangkiTsm() {
        return kebocoranTangkiTsm;
    }

    public void setKebocoranTangkiTsm(String kebocoranTangkiTsm) {
        this.kebocoranTangkiTsm = kebocoranTangkiTsm;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getSuhuSaatForcing() {
        return suhuSaatForcing;
    }

    public void setSuhuSaatForcing(double suhuSaatForcing) {
        this.suhuSaatForcing = suhuSaatForcing;
    }

    public String getCeklistKeaktifanAgitatorCameco() {
        return ceklistKeaktifanAgitatorCameco;
    }

    public void setCeklistKeaktifanAgitatorCameco(String ceklistKeaktifanAgitatorCameco) {
        this.ceklistKeaktifanAgitatorCameco = ceklistKeaktifanAgitatorCameco;
    }

    public String getCeklistKeaktifanAgitatorTangkiSuplay() {
        return ceklistKeaktifanAgitatorTangkiSuplay;
    }

    public void setCeklistKeaktifanAgitatorTangkiSuplay(String ceklistKeaktifanAgitatorTangkiSuplay) {
        this.ceklistKeaktifanAgitatorTangkiSuplay = ceklistKeaktifanAgitatorTangkiSuplay;
    }

    public String getVerifyMandor() {
        return verifyMandor;
    }

    public void setVerifyMandor(String verifyMandor) {
        this.verifyMandor = verifyMandor;
    }

    public String getVerifyKasi() {
        return verifyKasi;
    }

    public void setVerifyKasi(String verifyKasi) {
        this.verifyKasi = verifyKasi;
    }

    public String getVerifyKabag() {
        return verifyKabag;
    }

    public void setVerifyKabag(String verifyKabag) {
        this.verifyKabag = verifyKabag;
    }

    public String getUSERNAME() {
        return USERNAME;
    }

    public void setUSERNAME(String USERNAME) {
        this.USERNAME = USERNAME;
    }



}
