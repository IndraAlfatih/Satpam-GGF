package com.ggf.qcpp.d_hasilpengamatan.hasilPengamatanOffline.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

// Main Model Class
public class OfflineModel {
    @SerializedName("DATA")
    private List<Plot> data;

    @SerializedName("kategori")
    private String kategori;

    @SerializedName("lokasi")
    private String lokasi;

    @SerializedName("luas_netto")
    private String luasNetto;

    @SerializedName("no_line")
    private String noLine;

    @SerializedName("no_spk")
    private String noSpk;

    @SerializedName("NO_UNIT_IMPLEMENT")
    private String noUnitImplement;

    @SerializedName("pg")
    private String pg;

    @SerializedName("status_pengamatan")
    private String statusPengamatan;

    @SerializedName("username")
    private String username;

    @SerializedName("wil")
    private String wil;

    @SerializedName("keterangan")
    private String keterangan;

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    // Getters and Setters
    public List<Plot> getData() {
        return data;
    }

    public void setData(List<Plot> data) {
        this.data = data;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getLuasNetto() {
        return luasNetto;
    }

    public void setLuasNetto(String luasNetto) {
        this.luasNetto = luasNetto;
    }

    public String getNoLine() {
        return noLine;
    }

    public void setNoLine(String noLine) {
        this.noLine = noLine;
    }

    public String getNoSpk() {
        return noSpk;
    }

    public void setNoSpk(String noSpk) {
        this.noSpk = noSpk;
    }

    public String getNoUnitImplement() {
        return noUnitImplement;
    }

    public void setNoUnitImplement(String noUnitImplement) {
        this.noUnitImplement = noUnitImplement;
    }

    public String getPg() {
        return pg;
    }

    public void setPg(String pg) {
        this.pg = pg;
    }

    public String getStatusPengamatan() {
        return statusPengamatan;
    }

    public void setStatusPengamatan(String statusPengamatan) {
        this.statusPengamatan = statusPengamatan;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getWil() {
        return wil;
    }

    public void setWil(String wil) {
        this.wil = wil;
    }
}

// Plot class representing each item in the "DATA" list
class Plot {
    @SerializedName("PLOT")
    private String plot;

    @SerializedName("SAMPLE")
    private List<Sample> sample;

    // Getters and Setters
    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public List<Sample> getSample() {
        return sample;
    }

    public void setSample(List<Sample> sample) {
        this.sample = sample;
    }
}

// Sample class representing each sample in the "SAMPLE" list
class Sample {
    @SerializedName("no_spk2")
    public String no_spk2;
    @SerializedName("no_line")
    public String no_line;



    @SerializedName("reworking")
    private String reworking;

    //potensi crown
    @SerializedName("daun_lemas")
    public int daun_lemas;
    @SerializedName("daun_berduri")
    public int daun_berduri;
    @SerializedName("tumbuh_lebih_dari_1")
    public int tumbuh_lebih_dari_1;
    @SerializedName("tumbuh_tidak_ada")
    public int tumbuh_tidak_ada;
    @SerializedName("under_size")
    public int under_size;
    @SerializedName("rusak_mekanis")
    public int rusak_mekanis;
    @SerializedName("busuk")
    public int busuk;
    @SerializedName("bergejala")
    public int bergejala;
    @SerializedName("layu")
    public int layu;
    @SerializedName("cabang_lebih_dari_3")
    public int cabang_lebih_dari_3;


    @SerializedName("bibit_10_sampai_11")
    public int bibit_10_sampai_11;
    @SerializedName("bibit_12_sampai_14")
    public int bibit_12_sampai_14;
    @SerializedName("bibit_15_sampai_17")
    public int bibit_15_sampai_17;
    @SerializedName("bibit_18_sampai_24")
    public int bibit_18_sampai_24;
    @SerializedName("bibit_25_sampai_33")
    public int bibit_25_sampai_33;
    @SerializedName("bibit_34_sampai_38")
    public int bibit_34_sampai_38;
    @SerializedName("bibit_lebih_dari_38")
    public int bibit_lebih_dari_38;


    @SerializedName("normal")
    public int normal;
    @SerializedName("cabang")
    public int cabang;
    @SerializedName("liar")
    public int liar;

    @SerializedName("total_afkir")
    public int total_afkir;

    @SerializedName("total_bibit")
    public int total_bibit;

    @SerializedName("crown_cabang_2")
    public int crown_cabang_2;
    @SerializedName("crown_cabang_3")
    public int crown_cabang_3;

    @SerializedName("tanaman_mandul")
    public int tanaman_mandul;

    //choper
    @SerializedName("aplikasi_rapat")
    private Double aplikasiRapat;

    @SerializedName("bonggol_terpecah")
    private Double bonggolTerpecah;

    @SerializedName("plot")
    private String plot;

    @SerializedName("tanaman_hancur")
    private Double tanamanHancur;

    @SerializedName("keterangan")
    private String keterangan;

    @SerializedName("jenis_implement")
    private String jenisImplement;

    @SerializedName("lokasi")
    private String lokasi;

    @SerializedName("no_spk")
    private String noSpk;

    @SerializedName("no_sample")
    public int no_sample;

    @SerializedName("luas_plot")
    public Float luas_plot;

    @SerializedName("no_unit_implement")
    private String noUnitImplement;

    @SerializedName("status_pengamatan")
    private String statusPengamatan;

    @SerializedName("wil")
    private String wil;

    //bajak
    @SerializedName("comodity")
    public String comodity_bajak;

    @SerializedName("aplikasi_pinggiran")
    public String aplikasi_pinggiran;

    @SerializedName("kerataan_aplikasi")
    public String kerataan_aplikasi;


    @SerializedName("std_musim")
    public String std_musim;


    @SerializedName("jenis_bajak")
    public String std_jenisbajak;

    @SerializedName("kedalaman")
    public Float KEDALAMAN;

    @SerializedName("dead_furrow")
    public Float DEAD_FURROW;

    //finishing

    @SerializedName("lolos_ayakan")
    public String lolos_ayakan;

    @SerializedName("tidak_lolos_ayakan")
    public String tidak_lolos_ayakan;

    //Ridger

    @SerializedName("jarak_antar_poros_gulud")
    public float jarak_antar_poros_gulud;
    @SerializedName("kedalaman_kuku_ridge")
    public float kedalaman_kuku_ridger;

    //jalan Saluran

    @SerializedName("jalan_block")
    public float jalan_block;
    @SerializedName("jalan_plot")
    public float jalan_plot;
    @SerializedName("jalan_seksi")
    public float jalan_seksi;
    @SerializedName("jalan_perimeter")
    public float jalan_perimeter;

    @SerializedName("saluran_sekunder")
    public float saluran_sekunder;
    @SerializedName("saluran_tersier")
    public float saluran_tersier;
    @SerializedName("jalan_saluran_tidak_ada_ripper")
    public float jalan_saluran_tidak_ada_ripper;
    @SerializedName("saluran_tersier_examini")
    public float saluran_tersier_examini;
    @SerializedName("saluran_tersier_traktor")
    public float saluran_tersier_traktor;
    @SerializedName("jumlah_saluran_tersier")
    public int jumlah_saluran_tersier;

    @SerializedName("lebar_tersier_dinding_atas")
    public float lebar_tersier_dinding_atas;
    @SerializedName("lebar_tersier_erosi")
    public float lebar_tersier_erosi;
    @SerializedName("lebar_sekunder_dinding_atas")
    public float lebar_sekunder_dinding_atas;
    @SerializedName("lebar_sekunder_erosi")
    public float lebar_sekunder_erosi;

    //kebersihan bonggol

    @SerializedName("estimasi")
    public float ESTIMASI;

    @SerializedName("bonggol_segar_kurang_dari")
    public float BONGGOL_SEGAR_KURANG_DARI;

    @SerializedName("bonggol_segar_lebih_dari")
    public float BONGGOL_SEGAR_LEBIH_DARI;

    //ph tanah

    @SerializedName("luas_aktif")
    public String luas_aktif;

    @SerializedName("status_lokasi")
    public String status_lokasi;

    @SerializedName("ex_comodity")
    public String ex_comodity;
    @SerializedName("analisa_a1")
    public float analisa_a1;

    @SerializedName("analisa_a2")
    public float analisa_a2;

    @SerializedName("analisa_a3")
    public float analisa_a3;

    @SerializedName("analisa_a4")
    public float analisa_a4;
    @SerializedName("komposit_a1")
    public float komposit_a1;

    @SerializedName("komposit_a2")
    public float komposit_a2;
    @SerializedName("komposit_a3")
    public float komposit_a3;

    @SerializedName("komposit_a4")
    public float komposit_a4;

    //petik bibit
    @SerializedName("mandor_bibit")
    public String mandor_bibit;

    @SerializedName("jenis_bibit")
    public String jenis_bibit;

    @SerializedName("kelas_bibit")
    public String kelas_bibit;



    @SerializedName("label")
    public float label;

    @SerializedName("real")
    public float real;
    @SerializedName("bibit_normal")
    public float bibit_normal;

    @SerializedName("bibit_afkir")
    public float bibit_afkir;

    @SerializedName("bibit_over_plus")
    public int bibit_over_plus;
    @SerializedName("bibit_over")
    public int bibit_over;
    @SerializedName("bibit_1")
    public int bibit_1;

    @SerializedName("bibit_2")
    public int bibit_2;

    @SerializedName("bibit_3")
    public int bibit_3;

    @SerializedName("bibit_4")
    public int bibit_4;

    @SerializedName("bibit_5")
    public int bibit_5;

    @SerializedName("bibit_6")
    public int bibit_6;

    @SerializedName("bibit_7")
    public int bibit_7;

    ///transport

    @SerializedName("jumlah_tumpuk")
    public int jumlah_tumpuk;

    @SerializedName("rerata_tumpuk")
    public float rerata_tumpuk;

    @SerializedName("gulud")
    public float gulud;

    @SerializedName("dijalan")
    public float dijalan;
    @SerializedName("terlindas")
    public float terlindas;

    //pool dipiing

    @SerializedName("no_kendaraan")
    public String no_kendaraan;

    @SerializedName("jenis_unit")
    public String jenis_unit;

    @SerializedName("hasil")
    public float hasil;

    @SerializedName("informasi_bibit_terdipping")
    public int informasi_bibit_terdipping;



    @SerializedName("asal_do")
    public String asal_do;

    @SerializedName("tujuan_do")
    public String tujuan_do;

    @SerializedName("jumlah_sampel")
    public float jumlah_sampel;

    //kualitas tanam

    @SerializedName("status_jtab")
    public String status_jtab;
    @SerializedName("status_jtdb")
    public String status_jtdb;
    @SerializedName("update_peta")
    public String update_peta;
    @SerializedName("tot_tanamjtdb")
    public int tot_tanamjtdb;
    @SerializedName("tot_tanamjtab")
    public int tot_tanamjtab;
    @SerializedName("panjang_jtdb")
    public float panjang_jtdb;
    @SerializedName("panjang_jtab")
    public float panjang_jtab;
    @SerializedName("kedalaman_1")
    public float kedalaman_1;
    @SerializedName("kedalaman_2")
    public float kedalaman_2;
    @SerializedName("kedalaman_3")
    public float kedalaman_3;
    @SerializedName("kedalaman_4")
    public float kedalaman_4;

    @SerializedName("tot_tidaktegakterinjak")
    public int tot_tidaktegakterinjak;
    @SerializedName("tot_tegakterinjak")
    public int tot_tegakterinjak;

    //adukan


    @SerializedName("tanggal_pengamatan")
    public String tanggal_pengamatan;
    @SerializedName("lokasi_adukan")
    public String lokasi_adukan;
    @SerializedName("div")
    public String div;

    @SerializedName("kode_bsc")
    public String kode_bsc;

    @SerializedName("shift")
    public String shift;

    @SerializedName("aktivitas")
    public String aktivitas;

    @SerializedName("jenis_bahan")
    public String jenis_bahan;

    @SerializedName("rencana")
    public float rencana;

    @SerializedName("jenis_bahan_2")
    private String jenis_bahan_2;

    @SerializedName("rencana_2")
    private float rencana_2;

    @SerializedName("real_2")
    private float real_2;

    @SerializedName("jenis_bahan_3")
    private String jenis_bahan_3;

    @SerializedName("rencana_3")
    private float rencana_3;

    @SerializedName("real_3")
    private float real_3;

    @SerializedName("jenis_bahan_4")
    private String jenis_bahan_4;

    @SerializedName("rencana_4")
    private float rencana_4;

    @SerializedName("real_4")
    private float real_4;

    @SerializedName("jenis_bahan_5")
    private String jenis_bahan_5;

    @SerializedName("rencana_5")
    private float rencana_5;

    @SerializedName("real_5")
    private float real_5;

    @SerializedName("jenis_bahan_6")
    private String jenis_bahan_6;

    @SerializedName("rencana_6")
    private float rencana_6;

    @SerializedName("real_6")
    private float real_6;

    @SerializedName("jenis_bahan_7")
    private String jenis_bahan_7;

    @SerializedName("rencana_7")
    private float rencana_7;

    @SerializedName("real_7")
    private float real_7;

    @SerializedName("jenis_bahan_8")
    private String jenis_bahan_8;

    @SerializedName("rencana_8")
    private float rencana_8;

    @SerializedName("real_8")
    private float real_8;

    @SerializedName("jenis_bahan_9")
    private String jenis_bahan_9;

    @SerializedName("rencana_9")
    private float rencana_9;

    @SerializedName("real_9")
    private float real_9;

    @SerializedName("jenis_bahan_10")
    private String jenis_bahan_10;

    @SerializedName("rencana_10")
    private float rencana_10;

    @SerializedName("real_10")
    private float real_10;

    @SerializedName("pengisian_ke")
    public float pengisian_ke;

    @SerializedName("volume_air")
    public float volume_air;

    @SerializedName("tanggal_ditemukan_bibit_campur")
    public String tanggal_ditemukan_bibit_campur;

    @SerializedName("ceklist_keaktifan_agitator_cameco")
    public String ceklist_keaktifan_agitator_cameco;

    @SerializedName("kode_unit_bsc")
    private String kodeUnitBsc;


    @SerializedName("mulai_adukan_mixer_kecil")
    private String mulaiAdukanMixerKecil;

    @SerializedName("selesai_adukan_mixer_kecil")
    private String selesaiAdukanMixerKecil;

    @SerializedName("mulai_adukan_mixer_besar")
    private String mulaiAdukanMixerBesar;

    @SerializedName("selesai_adukan_mixer_besar")
    private String selesaiAdukanMixerBesar;

    @SerializedName("kode_unit_tangki_suplay")
    private String kodeUnitTangkiSuplay;

    @SerializedName("volume_air_tiga_perempat")
    private String volumeAirTigaPerempat;

    @SerializedName("rencana_volume_air")
    private float rencanaVolumeAir;

    @SerializedName("real_volume_air")
    private float realVolumeAir;

    @SerializedName("jenis_aplikasi")
    private String jenisAplikasi;

    @SerializedName("keterangan_pengisian")
    private String keteranganPengisian;

    @SerializedName("tangki_mixer")
    private String tangkiMixer;

    @SerializedName("cuci_bilas")
    private String cuciBilas;

    @SerializedName("tanggal_panen")
    public String tanggal_panen;

    @SerializedName("regu_panen")
    public String regu_panen;

    @SerializedName("tidak_crown")
    public int tidak_crown;

    @SerializedName("panjang_pengamatan")
    public int panjang_pengamatan;

    @SerializedName("normal_buah_tertinggal_besar")
    public int normal_buah_tertinggal_besar;

    @SerializedName("normal_buah_tertinggal_sedang")
    public int normal_buah_tertinggal_sedang;

    @SerializedName("normal_buah_tertinggal_kecil")
    public int normal_buah_tertinggal_kecil;

    @SerializedName("jumlah_sal_sekunder")
    public int jumlah_sal_sekunder;

    @SerializedName("jumlah_titik_pengamatan")
    public int jumlah_titik_pengamatan;

    @SerializedName("sekunder_buah_tertinggal_besar")
    public int sekunder_buah_tertinggal_besar;

    @SerializedName("sekunder_buah_tertinggal_sedang")
    public int sekunder_buah_tertinggal_sedang;

    @SerializedName("sekunder_buah_tertinggal_kecil")
    public int sekunder_buah_tertinggal_kecil;

    @SerializedName("jumlah_sal_tersier")
    public int jumlah_sal_tersier;

    @SerializedName("jumlah_titik_diamati")
    public int jumlah_titik_diamati;

    @SerializedName("tersier_buah_tertinggal_besar")
    public int tersier_buah_tertinggal_besar;

    @SerializedName("tersier_buah_tertinggal_sedang")
    public int tersier_buah_tertinggal_sedang;

    @SerializedName("tersier_buah_tertinggal_kecil")
    public int tersier_buah_tertinggal_kecil;

    @SerializedName("pengamatan_crown_tertinggal_baris_1_sampai_8")
    public int pengamatan_crown_tertinggal_baris_1_sampai_8;

    @SerializedName("pengamatan_crown_tertinggal_baris_9_sampai_35")
    public int pengamatan_crown_tertinggal_baris_9_sampai_35;

    @SerializedName("jumlah_baris")
    public int jumlah_baris;

    @SerializedName("total_crown")
    public int total_crown;

    @SerializedName("jalur")
    public String jalur;

    @SerializedName("statuslokasi")
    public String statuslokasi;

    @SerializedName("statuspengamatan")
    public String statuspengamatan;

    @SerializedName("crownnormal")
    public String crownnormal;

    @SerializedName("crownkipas")
    public String crownkipas;

    @SerializedName("crownbusuknormal")
    public String crownbusuknormal;

    @SerializedName("crownbusuktidaknormal")
    public String crownbusuktidaknormal;


    @SerializedName("pengamat")
    public String pengamat;
    @SerializedName("tk_seset")
    public String tk_seset;
    @SerializedName("jumlah_panjang_bonggol_kurang_dari_15")
    public int jumlah_panjang_bonggol_kurang_dari_15;
    @SerializedName("jumlah_panjang_bonggol_15_sampai_19")
    public int jumlah_panjang_bonggol_15_sampai_19;
    @SerializedName("jumlah_panjang_bonggol_20_sampai_22")
    public int jumlah_panjang_bonggol_20_sampai_22;
    @SerializedName("jumlah_panjang_bonggol_23_sampai_25")
    public int jumlah_panjang_bonggol_23_sampai_25;
    @SerializedName("jumlah_panjang_bonggol_26_sampai_28")
    public int jumlah_panjang_bonggol_26_sampai_28;
    @SerializedName("jumlah_panjang_bonggol_29_sampai_31")
    public int jumlah_panjang_bonggol_29_sampai_31;
    @SerializedName("jumlah_panjang_bonggol_lebih_dari_31")
    public int jumlah_panjang_bonggol_lebih_dari_31;
    @SerializedName("keterangan_jumlah_sucker")
    public String keterangan_jumlah_sucker;
    @SerializedName("keterangan_jumlah_indukan")
    public String keterangan_jumlah_indukan;

    @SerializedName("total_plot")
    public String total_plot;
    @SerializedName("plot_teramati")
    public String plot_teramati;
    @SerializedName("plot_normal")
    public String plot_normal;

    @SerializedName("luas_lokasi_aktif")
    public float luas_lokasi_aktif;
    @SerializedName("lebar_plot")
    public Float lebar_plot;

    @SerializedName("grade")
    public String grade;


    @SerializedName("rencana_panen")
    public String rencana_panen;

    @SerializedName("nomor_bibit")
    public String nomor_bibit;

    @SerializedName("tanggal_plot")
    public String tanggal_plot;


    @SerializedName("ce")
    public float ce;
    @SerializedName("lebar_jalan")
    public float lebar_jalan;
    @SerializedName("manual")
    public float manual;
    @SerializedName("examini")
    public int examini;
    @SerializedName("traktor")
    public int traktor;
    @SerializedName("ditcher")
    public int ditcher;

    @SerializedName("jumlah_baris_std")
    public int jumlah_baris_std;
    @SerializedName("penambahan_baris")
    public int penambahan_baris;

    @SerializedName("jumlah_pb")
    public int jumlah_pb;
    @SerializedName("tersier")
    public int tersier;
    @SerializedName("penambahan_baris_sal_tersier")
    public int penambahan_baris_sal_tersier;
    @SerializedName("kancingan")
    public int kancingan;





    public float getCe() {
        return ce;
    }

    public void setCe(float ce) {
        this.ce = ce;
    }

    public float getLebar_jalan() {
        return lebar_jalan;
    }

    public void setLebar_jalan(float lebar_jalan) {
        this.lebar_jalan = lebar_jalan;
    }

    public float getManual() {
        return manual;
    }

    public void setManual(float manual) {
        this.manual = manual;
    }

    public int getExamini() {
        return examini;
    }

    public void setExamini(int examini) {
        this.examini = examini;
    }

    public int getTraktor() {
        return traktor;
    }

    public void setTraktor(int traktor) {
        this.traktor = traktor;
    }

    public int getDitcher() {
        return ditcher;
    }

    public void setDitcher(int ditcher) {
        this.ditcher = ditcher;
    }

    public int getJumlah_baris_std() {
        return jumlah_baris_std;
    }

    public void setJumlah_baris_std(int jumlah_baris_std) {
        this.jumlah_baris_std = jumlah_baris_std;
    }

    public int getPenambahan_baris() {
        return penambahan_baris;
    }

    public void setPenambahan_baris(int penambahan_baris) {
        this.penambahan_baris = penambahan_baris;
    }

    public int getJumlah_pb() {
        return jumlah_pb;
    }

    public void setJumlah_pb(int jumlah_pb) {
        this.jumlah_pb = jumlah_pb;
    }

    public int getTersier() {
        return tersier;
    }

    public void setTersier(int tersier) {
        this.tersier = tersier;
    }

    public int getPenambahan_baris_sal_tersier() {
        return penambahan_baris_sal_tersier;
    }

    public void setPenambahan_baris_sal_tersier(int penambahan_baris_sal_tersier) {
        this.penambahan_baris_sal_tersier = penambahan_baris_sal_tersier;
    }

    public int getKancingan() {
        return kancingan;
    }

    public void setKancingan(int kancingan) {
        this.kancingan = kancingan;
    }

//    public String getMandor() {
//        return mandor;
//    }
//
//    public void setMandor(String mandor) {
//        this.mandor = mandor;
//    }

    public String getTanggal_plot() {
        return tanggal_plot;
    }

    public void setTanggal_plot(String tanggal_plot) {
        this.tanggal_plot = tanggal_plot;
    }

    public int getTidak_crown() {
        return tidak_crown;
    }

    public void setTidak_crown(int tidak_crown) {
        this.tidak_crown = tidak_crown;
    }

    public String getNomor_bibit() {
        return nomor_bibit;
    }

    public void setNomor_bibit(String nomor_bibit) {
        this.nomor_bibit = nomor_bibit;
    }

    public String getNo_spk2() {
        return no_spk2;
    }

    public void setNo_spk2(String no_spk2) {
        this.no_spk2 = no_spk2;
    }

    public String getNo_line() {
        return no_line;
    }

    public void setNo_line(String no_line) {
        this.no_line = no_line;
    }



    public String getJenis_bahan_2() {
        return jenis_bahan_2;
    }

    public void setJenis_bahan_2(String jenis_bahan_2) {
        this.jenis_bahan_2 = jenis_bahan_2;
    }

    public float getRencana_2() {
        return rencana_2;
    }

    public void setRencana_2(float rencana_2) {
        this.rencana_2 = rencana_2;
    }

    public float getReal_2() {
        return real_2;
    }

    public void setReal_2(float real_2) {
        this.real_2 = real_2;
    }

    public String getJenis_bahan_3() {
        return jenis_bahan_3;
    }

    public void setJenis_bahan_3(String jenis_bahan_3) {
        this.jenis_bahan_3 = jenis_bahan_3;
    }

    public float getRencana_3() {
        return rencana_3;
    }

    public void setRencana_3(float rencana_3) {
        this.rencana_3 = rencana_3;
    }

    public float getReal_3() {
        return real_3;
    }

    public void setReal_3(float real_3) {
        this.real_3 = real_3;
    }

    public String getJenis_bahan_4() {
        return jenis_bahan_4;
    }

    public void setJenis_bahan_4(String jenis_bahan_4) {
        this.jenis_bahan_4 = jenis_bahan_4;
    }

    public float getRencana_4() {
        return rencana_4;
    }

    public void setRencana_4(float rencana_4) {
        this.rencana_4 = rencana_4;
    }

    public float getReal_4() {
        return real_4;
    }

    public void setReal_4(float real_4) {
        this.real_4 = real_4;
    }

    public String getJenis_bahan_5() {
        return jenis_bahan_5;
    }

    public void setJenis_bahan_5(String jenis_bahan_5) {
        this.jenis_bahan_5 = jenis_bahan_5;
    }

    public float getRencana_5() {
        return rencana_5;
    }

    public void setRencana_5(float rencana_5) {
        this.rencana_5 = rencana_5;
    }

    public float getReal_5() {
        return real_5;
    }

    public void setReal_5(float real_5) {
        this.real_5 = real_5;
    }

    public String getJenis_bahan_6() {
        return jenis_bahan_6;
    }

    public void setJenis_bahan_6(String jenis_bahan_6) {
        this.jenis_bahan_6 = jenis_bahan_6;
    }

    public float getRencana_6() {
        return rencana_6;
    }

    public void setRencana_6(float rencana_6) {
        this.rencana_6 = rencana_6;
    }

    public float getReal_6() {
        return real_6;
    }

    public void setReal_6(float real_6) {
        this.real_6 = real_6;
    }

    public String getJenis_bahan_7() {
        return jenis_bahan_7;
    }

    public void setJenis_bahan_7(String jenis_bahan_7) {
        this.jenis_bahan_7 = jenis_bahan_7;
    }

    public float getRencana_7() {
        return rencana_7;
    }

    public void setRencana_7(float rencana_7) {
        this.rencana_7 = rencana_7;
    }

    public float getReal_7() {
        return real_7;
    }

    public void setReal_7(float real_7) {
        this.real_7 = real_7;
    }

    public String getJenis_bahan_8() {
        return jenis_bahan_8;
    }

    public void setJenis_bahan_8(String jenis_bahan_8) {
        this.jenis_bahan_8 = jenis_bahan_8;
    }

    public float getRencana_8() {
        return rencana_8;
    }

    public void setRencana_8(float rencana_8) {
        this.rencana_8 = rencana_8;
    }

    public float getReal_8() {
        return real_8;
    }

    public void setReal_8(float real_8) {
        this.real_8 = real_8;
    }

    public String getJenis_bahan_9() {
        return jenis_bahan_9;
    }

    public void setJenis_bahan_9(String jenis_bahan_9) {
        this.jenis_bahan_9 = jenis_bahan_9;
    }

    public float getRencana_9() {
        return rencana_9;
    }

    public void setRencana_9(float rencana_9) {
        this.rencana_9 = rencana_9;
    }

    public float getReal_9() {
        return real_9;
    }

    public void setReal_9(float real_9) {
        this.real_9 = real_9;
    }

    public String getJenis_bahan_10() {
        return jenis_bahan_10;
    }

    public void setJenis_bahan_10(String jenis_bahan_10) {
        this.jenis_bahan_10 = jenis_bahan_10;
    }

    public float getRencana_10() {
        return rencana_10;
    }

    public void setRencana_10(float rencana_10) {
        this.rencana_10 = rencana_10;
    }

    public float getReal_10() {
        return real_10;
    }

    public void setReal_10(float real_10) {
        this.real_10 = real_10;
    }

    public String getLokasi_adukan() {
        return lokasi_adukan;
    }

    public void setLokasi_adukan(String lokasi_adukan) {
        this.lokasi_adukan = lokasi_adukan;
    }

    public String getRencana_panen() {
        return rencana_panen;
    }

    public void setRencana_panen(String rencana_panen) {
        this.rencana_panen = rencana_panen;
    }

    public String getReworking() {
        return reworking;
    }

    public void setReworking(String reworking) {
        this.reworking = reworking;
    }

    public int getDaun_lemas() {
        return daun_lemas;
    }

    public void setDaun_lemas(int daun_lemas) {
        this.daun_lemas = daun_lemas;
    }

    public int getDaun_berduri() {
        return daun_berduri;
    }

    public void setDaun_berduri(int daun_berduri) {
        this.daun_berduri = daun_berduri;
    }

    public int getTumbuh_lebih_dari_1() {
        return tumbuh_lebih_dari_1;
    }

    public void setTumbuh_lebih_dari_1(int tumbuh_lebih_dari_1) {
        this.tumbuh_lebih_dari_1 = tumbuh_lebih_dari_1;
    }

    public int getTumbuh_tidak_ada() {
        return tumbuh_tidak_ada;
    }

    public void setTumbuh_tidak_ada(int tumbuh_tidak_ada) {
        this.tumbuh_tidak_ada = tumbuh_tidak_ada;
    }

    public int getUnder_size() {
        return under_size;
    }

    public void setUnder_size(int under_size) {
        this.under_size = under_size;
    }

    public int getRusak_mekanis() {
        return rusak_mekanis;
    }

    public void setRusak_mekanis(int rusak_mekanis) {
        this.rusak_mekanis = rusak_mekanis;
    }

    public int getBusuk() {
        return busuk;
    }

    public void setBusuk(int busuk) {
        this.busuk = busuk;
    }

    public int getBergejala() {
        return bergejala;
    }

    public void setBergejala(int bergejala) {
        this.bergejala = bergejala;
    }

    public int getLayu() {
        return layu;
    }

    public void setLayu(int layu) {
        this.layu = layu;
    }

    public int getCabang_lebih_dari_3() {
        return cabang_lebih_dari_3;
    }

    public void setCabang_lebih_dari_3(int cabang_lebih_dari_3) {
        this.cabang_lebih_dari_3 = cabang_lebih_dari_3;
    }

    public int getBibit_10_sampai_11() {
        return bibit_10_sampai_11;
    }

    public void setBibit_10_sampai_11(int bibit_10_sampai_11) {
        this.bibit_10_sampai_11 = bibit_10_sampai_11;
    }

    public int getBibit_12_sampai_14() {
        return bibit_12_sampai_14;
    }

    public void setBibit_12_sampai_14(int bibit_12_sampai_14) {
        this.bibit_12_sampai_14 = bibit_12_sampai_14;
    }

    public int getBibit_15_sampai_17() {
        return bibit_15_sampai_17;
    }

    public void setBibit_15_sampai_17(int bibit_15_sampai_17) {
        this.bibit_15_sampai_17 = bibit_15_sampai_17;
    }

    public int getBibit_18_sampai_24() {
        return bibit_18_sampai_24;
    }

    public void setBibit_18_sampai_24(int bibit_18_sampai_24) {
        this.bibit_18_sampai_24 = bibit_18_sampai_24;
    }

    public int getBibit_25_sampai_33() {
        return bibit_25_sampai_33;
    }

    public void setBibit_25_sampai_33(int bibit_25_sampai_33) {
        this.bibit_25_sampai_33 = bibit_25_sampai_33;
    }

    public int getBibit_34_sampai_38() {
        return bibit_34_sampai_38;
    }

    public void setBibit_34_sampai_38(int bibit_34_sampai_38) {
        this.bibit_34_sampai_38 = bibit_34_sampai_38;
    }

    public int getBibit_lebih_dari_38() {
        return bibit_lebih_dari_38;
    }

    public void setBibit_lebih_dari_38(int bibit_lebih_dari_38) {
        this.bibit_lebih_dari_38 = bibit_lebih_dari_38;
    }

    public int getNormal() {
        return normal;
    }

    public void setNormal(int normal) {
        this.normal = normal;
    }

    public int getCabang() {
        return cabang;
    }

    public void setCabang(int cabang) {
        this.cabang = cabang;
    }

    public int getLiar() {
        return liar;
    }

    public void setLiar(int liar) {
        this.liar = liar;
    }

    public int getTotal_afkir() {
        return total_afkir;
    }

    public void setTotal_afkir(int total_afkir) {
        this.total_afkir = total_afkir;
    }

    public int getTotal_bibit() {
        return total_bibit;
    }

    public void setTotal_bibit(int total_bibit) {
        this.total_bibit = total_bibit;
    }

    public int getCrown_cabang_2() {
        return crown_cabang_2;
    }

    public void setCrown_cabang_2(int crown_cabang_2) {
        this.crown_cabang_2 = crown_cabang_2;
    }

    public int getCrown_cabang_3() {
        return crown_cabang_3;
    }

    public void setCrown_cabang_3(int crown_cabang_3) {
        this.crown_cabang_3 = crown_cabang_3;
    }

    public int getTanaman_mandul() {
        return tanaman_mandul;
    }

    public void setTanaman_mandul(int tanaman_mandul) {
        this.tanaman_mandul = tanaman_mandul;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public float getLuas_lokasi_aktif() {
        return luas_lokasi_aktif;
    }

    public void setLuas_lokasi_aktif(float luas_lokasi_aktif) {
        this.luas_lokasi_aktif = luas_lokasi_aktif;
    }

    public Float getLebar_plot() {
        return lebar_plot;
    }

    public void setLebar_plot(Float lebar_plot) {
        this.lebar_plot = lebar_plot;
    }

    public String getTanggal_pengamatan() {
        return tanggal_pengamatan;
    }

    public void setTanggal_pengamatan(String tanggal_pengamatan) {
        this.tanggal_pengamatan = tanggal_pengamatan;
    }

    public String getDiv() {
        return div;
    }

    public void setDiv(String div) {
        this.div = div;
    }

    public String getKode_bsc() {
        return kode_bsc;
    }

    public void setKode_bsc(String kode_bsc) {
        this.kode_bsc = kode_bsc;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public String getAktivitas() {
        return aktivitas;
    }

    public void setAktivitas(String aktivitas) {
        this.aktivitas = aktivitas;
    }

    public String getJenis_bahan() {
        return jenis_bahan;
    }

    public void setJenis_bahan(String jenis_bahan) {
        this.jenis_bahan = jenis_bahan;
    }

    public float getRencana() {
        return rencana;
    }

    public void setRencana(float rencana) {
        this.rencana = rencana;
    }


    public float getPengisian_ke() {
        return pengisian_ke;
    }

    public void setPengisian_ke(float pengisian_ke) {
        this.pengisian_ke = pengisian_ke;
    }

    public float getVolume_air() {
        return volume_air;
    }

    public void setVolume_air(float volume_air) {
        this.volume_air = volume_air;
    }

    public String getCeklist_keaktifan_agitator_cameco() {
        return ceklist_keaktifan_agitator_cameco;
    }

    public void setCeklist_keaktifan_agitator_cameco(String ceklist_keaktifan_agitator_cameco) {
        this.ceklist_keaktifan_agitator_cameco = ceklist_keaktifan_agitator_cameco;
    }

    public String getKodeUnitBsc() {
        return kodeUnitBsc;
    }

    public void setKodeUnitBsc(String kodeUnitBsc) {
        this.kodeUnitBsc = kodeUnitBsc;
    }

    public String getMulaiAdukanMixerKecil() {
        return mulaiAdukanMixerKecil;
    }

    public void setMulaiAdukanMixerKecil(String mulaiAdukanMixerKecil) {
        this.mulaiAdukanMixerKecil = mulaiAdukanMixerKecil;
    }

    public String getSelesaiAdukanMixerKecil() {
        return selesaiAdukanMixerKecil;
    }

    public void setSelesaiAdukanMixerKecil(String selesaiAdukanMixerKecil) {
        this.selesaiAdukanMixerKecil = selesaiAdukanMixerKecil;
    }

    public String getMulaiAdukanMixerBesar() {
        return mulaiAdukanMixerBesar;
    }

    public void setMulaiAdukanMixerBesar(String mulaiAdukanMixerBesar) {
        this.mulaiAdukanMixerBesar = mulaiAdukanMixerBesar;
    }

    public String getSelesaiAdukanMixerBesar() {
        return selesaiAdukanMixerBesar;
    }

    public void setSelesaiAdukanMixerBesar(String selesaiAdukanMixerBesar) {
        this.selesaiAdukanMixerBesar = selesaiAdukanMixerBesar;
    }

    public String getKodeUnitTangkiSuplay() {
        return kodeUnitTangkiSuplay;
    }

    public void setKodeUnitTangkiSuplay(String kodeUnitTangkiSuplay) {
        this.kodeUnitTangkiSuplay = kodeUnitTangkiSuplay;
    }

    public String getVolumeAirTigaPerempat() {
        return volumeAirTigaPerempat;
    }

    public void setVolumeAirTigaPerempat(String volumeAirTigaPerempat) {
        this.volumeAirTigaPerempat = volumeAirTigaPerempat;
    }

    public float getRencanaVolumeAir() {
        return rencanaVolumeAir;
    }

    public void setRencanaVolumeAir(float rencanaVolumeAir) {
        this.rencanaVolumeAir = rencanaVolumeAir;
    }

    public float getRealVolumeAir() {
        return realVolumeAir;
    }

    public void setRealVolumeAir(float realVolumeAir) {
        this.realVolumeAir = realVolumeAir;
    }

    public String getJenisAplikasi() {
        return jenisAplikasi;
    }

    public void setJenisAplikasi(String jenisAplikasi) {
        this.jenisAplikasi = jenisAplikasi;
    }

    public String getKeteranganPengisian() {
        return keteranganPengisian;
    }

    public void setKeteranganPengisian(String keteranganPengisian) {
        this.keteranganPengisian = keteranganPengisian;
    }

    public String getTangkiMixer() {
        return tangkiMixer;
    }

    public void setTangkiMixer(String tangkiMixer) {
        this.tangkiMixer = tangkiMixer;
    }

    public String getCuciBilas() {
        return cuciBilas;
    }

    public void setCuciBilas(String cuciBilas) {
        this.cuciBilas = cuciBilas;
    }

    public String getTanggal_panen() {
        return tanggal_panen;
    }

    public void setTanggal_panen(String tanggal_panen) {
        this.tanggal_panen = tanggal_panen;
    }

    public String getRegu_panen() {
        return regu_panen;
    }

    public void setRegu_panen(String regu_panen) {
        this.regu_panen = regu_panen;
    }

    public int getPanjang_pengamatan() {
        return panjang_pengamatan;
    }

    public void setPanjang_pengamatan(int panjang_pengamatan) {
        this.panjang_pengamatan = panjang_pengamatan;
    }

    public int getNormal_buah_tertinggal_besar() {
        return normal_buah_tertinggal_besar;
    }

    public void setNormal_buah_tertinggal_besar(int normal_buah_tertinggal_besar) {
        this.normal_buah_tertinggal_besar = normal_buah_tertinggal_besar;
    }

    public int getNormal_buah_tertinggal_sedang() {
        return normal_buah_tertinggal_sedang;
    }

    public void setNormal_buah_tertinggal_sedang(int normal_buah_tertinggal_sedang) {
        this.normal_buah_tertinggal_sedang = normal_buah_tertinggal_sedang;
    }

    public int getNormal_buah_tertinggal_kecil() {
        return normal_buah_tertinggal_kecil;
    }

    public void setNormal_buah_tertinggal_kecil(int normal_buah_tertinggal_kecil) {
        this.normal_buah_tertinggal_kecil = normal_buah_tertinggal_kecil;
    }

    public int getJumlah_sal_sekunder() {
        return jumlah_sal_sekunder;
    }

    public void setJumlah_sal_sekunder(int jumlah_sal_sekunder) {
        this.jumlah_sal_sekunder = jumlah_sal_sekunder;
    }

    public int getJumlah_titik_pengamatan() {
        return jumlah_titik_pengamatan;
    }

    public void setJumlah_titik_pengamatan(int jumlah_titik_pengamatan) {
        this.jumlah_titik_pengamatan = jumlah_titik_pengamatan;
    }

    public int getSekunder_buah_tertinggal_besar() {
        return sekunder_buah_tertinggal_besar;
    }

    public void setSekunder_buah_tertinggal_besar(int sekunder_buah_tertinggal_besar) {
        this.sekunder_buah_tertinggal_besar = sekunder_buah_tertinggal_besar;
    }

    public int getSekunder_buah_tertinggal_sedang() {
        return sekunder_buah_tertinggal_sedang;
    }

    public void setSekunder_buah_tertinggal_sedang(int sekunder_buah_tertinggal_sedang) {
        this.sekunder_buah_tertinggal_sedang = sekunder_buah_tertinggal_sedang;
    }

    public int getSekunder_buah_tertinggal_kecil() {
        return sekunder_buah_tertinggal_kecil;
    }

    public void setSekunder_buah_tertinggal_kecil(int sekunder_buah_tertinggal_kecil) {
        this.sekunder_buah_tertinggal_kecil = sekunder_buah_tertinggal_kecil;
    }

    public int getJumlah_sal_tersier() {
        return jumlah_sal_tersier;
    }

    public void setJumlah_sal_tersier(int jumlah_sal_tersier) {
        this.jumlah_sal_tersier = jumlah_sal_tersier;
    }

    public int getJumlah_titik_diamati() {
        return jumlah_titik_diamati;
    }

    public void setJumlah_titik_diamati(int jumlah_titik_diamati) {
        this.jumlah_titik_diamati = jumlah_titik_diamati;
    }

    public int getTersier_buah_tertinggal_besar() {
        return tersier_buah_tertinggal_besar;
    }

    public void setTersier_buah_tertinggal_besar(int tersier_buah_tertinggal_besar) {
        this.tersier_buah_tertinggal_besar = tersier_buah_tertinggal_besar;
    }

    public int getTersier_buah_tertinggal_sedang() {
        return tersier_buah_tertinggal_sedang;
    }

    public void setTersier_buah_tertinggal_sedang(int tersier_buah_tertinggal_sedang) {
        this.tersier_buah_tertinggal_sedang = tersier_buah_tertinggal_sedang;
    }

    public int getTersier_buah_tertinggal_kecil() {
        return tersier_buah_tertinggal_kecil;
    }

    public void setTersier_buah_tertinggal_kecil(int tersier_buah_tertinggal_kecil) {
        this.tersier_buah_tertinggal_kecil = tersier_buah_tertinggal_kecil;
    }

    public int getPengamatan_crown_tertinggal_baris_1_sampai_8() {
        return pengamatan_crown_tertinggal_baris_1_sampai_8;
    }

    public void setPengamatan_crown_tertinggal_baris_1_sampai_8(int pengamatan_crown_tertinggal_baris_1_sampai_8) {
        this.pengamatan_crown_tertinggal_baris_1_sampai_8 = pengamatan_crown_tertinggal_baris_1_sampai_8;
    }

    public int getPengamatan_crown_tertinggal_baris_9_sampai_35() {
        return pengamatan_crown_tertinggal_baris_9_sampai_35;
    }

    public void setPengamatan_crown_tertinggal_baris_9_sampai_35(int pengamatan_crown_tertinggal_baris_9_sampai_35) {
        this.pengamatan_crown_tertinggal_baris_9_sampai_35 = pengamatan_crown_tertinggal_baris_9_sampai_35;
    }

    public int getJumlah_baris() {
        return jumlah_baris;
    }

    public void setJumlah_baris(int jumlah_baris) {
        this.jumlah_baris = jumlah_baris;
    }

    public int getTotal_crown() {
        return total_crown;
    }

    public void setTotal_crown(int total_crown) {
        this.total_crown = total_crown;
    }

    public String getJalur() {
        return jalur;
    }

    public void setJalur(String jalur) {
        this.jalur = jalur;
    }

    public String getStatuslokasi() {
        return statuslokasi;
    }

    public void setStatuslokasi(String statuslokasi) {
        this.statuslokasi = statuslokasi;
    }

    public String getStatuspengamatan() {
        return statuspengamatan;
    }

    public void setStatuspengamatan(String statuspengamatan) {
        this.statuspengamatan = statuspengamatan;
    }

    public String getCrownnormal() {
        return crownnormal;
    }

    public void setCrownnormal(String crownnormal) {
        this.crownnormal = crownnormal;
    }

    public String getCrownkipas() {
        return crownkipas;
    }

    public void setCrownkipas(String crownkipas) {
        this.crownkipas = crownkipas;
    }

    public String getCrownbusuknormal() {
        return crownbusuknormal;
    }

    public void setCrownbusuknormal(String crownbusuknormal) {
        this.crownbusuknormal = crownbusuknormal;
    }

    public String getCrownbusuktidaknormal() {
        return crownbusuktidaknormal;
    }

    public void setCrownbusuktidaknormal(String crownbusuktidaknormal) {
        this.crownbusuktidaknormal = crownbusuktidaknormal;
    }

    public String getPengamat() {
        return pengamat;
    }

    public void setPengamat(String pengamat) {
        this.pengamat = pengamat;
    }

    public String getTk_seset() {
        return tk_seset;
    }

    public void setTk_seset(String tk_seset) {
        this.tk_seset = tk_seset;
    }

    public int getJumlah_panjang_bonggol_kurang_dari_15() {
        return jumlah_panjang_bonggol_kurang_dari_15;
    }

    public void setJumlah_panjang_bonggol_kurang_dari_15(int jumlah_panjang_bonggol_kurang_dari_15) {
        this.jumlah_panjang_bonggol_kurang_dari_15 = jumlah_panjang_bonggol_kurang_dari_15;
    }

    public int getJumlah_panjang_bonggol_15_sampai_19() {
        return jumlah_panjang_bonggol_15_sampai_19;
    }

    public void setJumlah_panjang_bonggol_15_sampai_19(int jumlah_panjang_bonggol_15_sampai_19) {
        this.jumlah_panjang_bonggol_15_sampai_19 = jumlah_panjang_bonggol_15_sampai_19;
    }

    public int getJumlah_panjang_bonggol_20_sampai_22() {
        return jumlah_panjang_bonggol_20_sampai_22;
    }

    public void setJumlah_panjang_bonggol_20_sampai_22(int jumlah_panjang_bonggol_20_sampai_22) {
        this.jumlah_panjang_bonggol_20_sampai_22 = jumlah_panjang_bonggol_20_sampai_22;
    }

    public int getJumlah_panjang_bonggol_23_sampai_25() {
        return jumlah_panjang_bonggol_23_sampai_25;
    }

    public void setJumlah_panjang_bonggol_23_sampai_25(int jumlah_panjang_bonggol_23_sampai_25) {
        this.jumlah_panjang_bonggol_23_sampai_25 = jumlah_panjang_bonggol_23_sampai_25;
    }

    public int getJumlah_panjang_bonggol_26_sampai_28() {
        return jumlah_panjang_bonggol_26_sampai_28;
    }

    public void setJumlah_panjang_bonggol_26_sampai_28(int jumlah_panjang_bonggol_26_sampai_28) {
        this.jumlah_panjang_bonggol_26_sampai_28 = jumlah_panjang_bonggol_26_sampai_28;
    }

    public int getJumlah_panjang_bonggol_29_sampai_31() {
        return jumlah_panjang_bonggol_29_sampai_31;
    }

    public void setJumlah_panjang_bonggol_29_sampai_31(int jumlah_panjang_bonggol_29_sampai_31) {
        this.jumlah_panjang_bonggol_29_sampai_31 = jumlah_panjang_bonggol_29_sampai_31;
    }

    public int getJumlah_panjang_bonggol_lebih_dari_31() {
        return jumlah_panjang_bonggol_lebih_dari_31;
    }

    public void setJumlah_panjang_bonggol_lebih_dari_31(int jumlah_panjang_bonggol_lebih_dari_31) {
        this.jumlah_panjang_bonggol_lebih_dari_31 = jumlah_panjang_bonggol_lebih_dari_31;
    }

    public String getKeterangan_jumlah_sucker() {
        return keterangan_jumlah_sucker;
    }

    public void setKeterangan_jumlah_sucker(String keterangan_jumlah_sucker) {
        this.keterangan_jumlah_sucker = keterangan_jumlah_sucker;
    }

    public String getTanggal_ditemukan_bibit_campur() {
        return tanggal_ditemukan_bibit_campur;
    }

    public void setTanggal_ditemukan_bibit_campur(String tanggal_ditemukan_bibit_campur) {
        this.tanggal_ditemukan_bibit_campur = tanggal_ditemukan_bibit_campur;
    }

    public String getKeterangan_jumlah_indukan() {
        return keterangan_jumlah_indukan;
    }

    public void setKeterangan_jumlah_indukan(String keterangan_jumlah_indukan) {
        this.keterangan_jumlah_indukan = keterangan_jumlah_indukan;
    }

    public String getTotal_plot() {
        return total_plot;
    }

    public void setTotal_plot(String total_plot) {
        this.total_plot = total_plot;
    }

    public String getPlot_teramati() {
        return plot_teramati;
    }

    public void setPlot_teramati(String plot_teramati) {
        this.plot_teramati = plot_teramati;
    }

    public String getPlot_normal() {
        return plot_normal;
    }

    public void setPlot_normal(String plot_normal) {
        this.plot_normal = plot_normal;
    }

    public String getNo_kendaraan() {
        return no_kendaraan;
    }

    public void setNo_kendaraan(String no_kendaraan) {
        this.no_kendaraan = no_kendaraan;
    }

    public String getJenis_unit() {
        return jenis_unit;
    }

    public void setJenis_unit(String jenis_unit) {
        this.jenis_unit = jenis_unit;
    }

    public float getHasil() {
        return hasil;
    }

    public void setHasil(float hasil) {
        this.hasil = hasil;
    }

    public int getInformasi_bibit_terdipping() {
        return informasi_bibit_terdipping;
    }

    public void setInformasi_bibit_terdipping(int informasi_bibit_terdipping) {
        this.informasi_bibit_terdipping = informasi_bibit_terdipping;
    }

    public String getAsal_do() {
        return asal_do;
    }

    public void setAsal_do(String asal_do) {
        this.asal_do = asal_do;
    }

    public String getTujuan_do() {
        return tujuan_do;
    }

    public void setTujuan_do(String tujuan_do) {
        this.tujuan_do = tujuan_do;
    }

    public float getJumlah_sampel() {
        return jumlah_sampel;
    }

    public void setJumlah_sampel(float jumlah_sampel) {
        this.jumlah_sampel = jumlah_sampel;
    }

    public String getStatus_jtab() {
        return status_jtab;
    }

    public void setStatus_jtab(String status_jtab) {
        this.status_jtab = status_jtab;
    }

    public String getStatus_jtdb() {
        return status_jtdb;
    }

    public void setStatus_jtdb(String status_jtdb) {
        this.status_jtdb = status_jtdb;
    }

    public String getUpdate_peta() {
        return update_peta;
    }

    public void setUpdate_peta(String update_peta) {
        this.update_peta = update_peta;
    }

    public int getTot_tanamjtdb() {
        return tot_tanamjtdb;
    }

    public void setTot_tanamjtdb(int tot_tanamjtdb) {
        this.tot_tanamjtdb = tot_tanamjtdb;
    }

    public int getTot_tanamjtab() {
        return tot_tanamjtab;
    }

    public void setTot_tanamjtab(int tot_tanamjtab) {
        this.tot_tanamjtab = tot_tanamjtab;
    }

    public float getPanjang_jtdb() {
        return panjang_jtdb;
    }

    public void setPanjang_jtdb(float panjang_jtdb) {
        this.panjang_jtdb = panjang_jtdb;
    }

    public float getPanjang_jtab() {
        return panjang_jtab;
    }

    public void setPanjang_jtab(float panjang_jtab) {
        this.panjang_jtab = panjang_jtab;
    }

    public float getKedalaman_1() {
        return kedalaman_1;
    }

    public void setKedalaman_1(float kedalaman_1) {
        this.kedalaman_1 = kedalaman_1;
    }

    public float getKedalaman_2() {
        return kedalaman_2;
    }

    public void setKedalaman_2(float kedalaman_2) {
        this.kedalaman_2 = kedalaman_2;
    }

    public float getKedalaman_3() {
        return kedalaman_3;
    }

    public void setKedalaman_3(float kedalaman_3) {
        this.kedalaman_3 = kedalaman_3;
    }

    public float getKedalaman_4() {
        return kedalaman_4;
    }

    public void setKedalaman_4(float kedalaman_4) {
        this.kedalaman_4 = kedalaman_4;
    }

    public int getTot_tidaktegakterinjak() {
        return tot_tidaktegakterinjak;
    }

    public void setTot_tidaktegakterinjak(int tot_tidaktegakterinjak) {
        this.tot_tidaktegakterinjak = tot_tidaktegakterinjak;
    }

    public int getTot_tegakterinjak() {
        return tot_tegakterinjak;
    }

    public void setTot_tegakterinjak(int tot_tegakterinjak) {
        this.tot_tegakterinjak = tot_tegakterinjak;
    }

    public int getJumlah_tumpuk() {
        return jumlah_tumpuk;
    }

    public void setJumlah_tumpuk(int jumlah_tumpuk) {
        this.jumlah_tumpuk = jumlah_tumpuk;
    }

    public float getRerata_tumpuk() {
        return rerata_tumpuk;
    }

    public void setRerata_tumpuk(float rerata_tumpuk) {
        this.rerata_tumpuk = rerata_tumpuk;
    }

    public float getGulud() {
        return gulud;
    }

    public void setGulud(float gulud) {
        this.gulud = gulud;
    }

    public float getDijalan() {
        return dijalan;
    }

    public void setDijalan(float dijalan) {
        this.dijalan = dijalan;
    }

    public float getTerlindas() {
        return terlindas;
    }

    public void setTerlindas(float terlindas) {
        this.terlindas = terlindas;
    }

    public String getMandor_bibit() {
        return mandor_bibit;
    }

    public void setMandor_bibit(String mandor_bibit) {
        this.mandor_bibit = mandor_bibit;
    }

    public String getJenis_bibit() {
        return jenis_bibit;
    }

    public void setJenis_bibit(String jenis_bibit) {
        this.jenis_bibit = jenis_bibit;
    }

    public String getKelas_bibit() {
        return kelas_bibit;
    }

    public void setKelas_bibit(String kelas_bibit) {
        this.kelas_bibit = kelas_bibit;
    }

    public float getLabel() {
        return label;
    }

    public void setLabel(float label) {
        this.label = label;
    }

    public float getReal() {
        return real;
    }

    public void setReal(float real) {
        this.real = real;
    }

    public float getBibit_normal() {
        return bibit_normal;
    }

    public void setBibit_normal(float bibit_normal) {
        this.bibit_normal = bibit_normal;
    }

    public float getBibit_afkir() {
        return bibit_afkir;
    }

    public void setBibit_afkir(float bibit_afkir) {
        this.bibit_afkir = bibit_afkir;
    }

    public int getBibit_over_plus() {
        return bibit_over_plus;
    }

    public void setBibit_over_plus(int bibit_over_plus) {
        this.bibit_over_plus = bibit_over_plus;
    }

    public int getBibit_over() {
        return bibit_over;
    }

    public void setBibit_over(int bibit_over) {
        this.bibit_over = bibit_over;
    }

    public int getBibit_1() {
        return bibit_1;
    }

    public void setBibit_1(int bibit_1) {
        this.bibit_1 = bibit_1;
    }

    public int getBibit_2() {
        return bibit_2;
    }

    public void setBibit_2(int bibit_2) {
        this.bibit_2 = bibit_2;
    }

    public int getBibit_3() {
        return bibit_3;
    }

    public void setBibit_3(int bibit_3) {
        this.bibit_3 = bibit_3;
    }

    public int getBibit_4() {
        return bibit_4;
    }

    public void setBibit_4(int bibit_4) {
        this.bibit_4 = bibit_4;
    }

    public int getBibit_5() {
        return bibit_5;
    }

    public void setBibit_5(int bibit_5) {
        this.bibit_5 = bibit_5;
    }

    public int getBibit_6() {
        return bibit_6;
    }

    public void setBibit_6(int bibit_6) {
        this.bibit_6 = bibit_6;
    }

    public int getBibit_7() {
        return bibit_7;
    }

    public void setBibit_7(int bibit_7) {
        this.bibit_7 = bibit_7;
    }

    public String getLuas_aktif() {
        return luas_aktif;
    }

    public void setLuas_aktif(String luas_aktif) {
        this.luas_aktif = luas_aktif;
    }

    public String getStatus_lokasi() {
        return status_lokasi;
    }

    public void setStatus_lokasi(String status_lokasi) {
        this.status_lokasi = status_lokasi;
    }

    public String getEx_comodity() {
        return ex_comodity;
    }

    public void setEx_comodity(String ex_comodity) {
        this.ex_comodity = ex_comodity;
    }

    public float getAnalisa_a1() {
        return analisa_a1;
    }

    public void setAnalisa_a1(float analisa_a1) {
        this.analisa_a1 = analisa_a1;
    }

    public float getAnalisa_a2() {
        return analisa_a2;
    }

    public void setAnalisa_a2(float analisa_a2) {
        this.analisa_a2 = analisa_a2;
    }

    public float getAnalisa_a3() {
        return analisa_a3;
    }

    public void setAnalisa_a3(float analisa_a3) {
        this.analisa_a3 = analisa_a3;
    }

    public float getAnalisa_a4() {
        return analisa_a4;
    }

    public void setAnalisa_a4(float analisa_a4) {
        this.analisa_a4 = analisa_a4;
    }

    public float getKomposit_a1() {
        return komposit_a1;
    }

    public void setKomposit_a1(float komposit_a1) {
        this.komposit_a1 = komposit_a1;
    }

    public float getKomposit_a2() {
        return komposit_a2;
    }

    public void setKomposit_a2(float komposit_a2) {
        this.komposit_a2 = komposit_a2;
    }

    public float getKomposit_a3() {
        return komposit_a3;
    }

    public void setKomposit_a3(float komposit_a3) {
        this.komposit_a3 = komposit_a3;
    }

    public float getKomposit_a4() {
        return komposit_a4;
    }

    public void setKomposit_a4(float komposit_a4) {
        this.komposit_a4 = komposit_a4;
    }

    public float getESTIMASI() {
        return ESTIMASI;
    }

    public void setESTIMASI(float ESTIMASI) {
        this.ESTIMASI = ESTIMASI;
    }

    public float getBONGGOL_SEGAR_KURANG_DARI() {
        return BONGGOL_SEGAR_KURANG_DARI;
    }

    public void setBONGGOL_SEGAR_KURANG_DARI(float BONGGOL_SEGAR_KURANG_DARI) {
        this.BONGGOL_SEGAR_KURANG_DARI = BONGGOL_SEGAR_KURANG_DARI;
    }

    public float getBONGGOL_SEGAR_LEBIH_DARI() {
        return BONGGOL_SEGAR_LEBIH_DARI;
    }

    public void setBONGGOL_SEGAR_LEBIH_DARI(float BONGGOL_SEGAR_LEBIH_DARI) {
        this.BONGGOL_SEGAR_LEBIH_DARI = BONGGOL_SEGAR_LEBIH_DARI;
    }

    public float getJalan_block() {
        return jalan_block;
    }

    public void setJalan_block(float jalan_block) {
        this.jalan_block = jalan_block;
    }

    public float getJalan_plot() {
        return jalan_plot;
    }

    public void setJalan_plot(float jalan_plot) {
        this.jalan_plot = jalan_plot;
    }

    public float getJalan_seksi() {
        return jalan_seksi;
    }

    public void setJalan_seksi(float jalan_seksi) {
        this.jalan_seksi = jalan_seksi;
    }

    public float getJalan_perimeter() {
        return jalan_perimeter;
    }

    public void setJalan_perimeter(float jalan_perimeter) {
        this.jalan_perimeter = jalan_perimeter;
    }

    public float getSaluran_sekunder() {
        return saluran_sekunder;
    }

    public void setSaluran_sekunder(float saluran_sekunder) {
        this.saluran_sekunder = saluran_sekunder;
    }

    public float getSaluran_tersier() {
        return saluran_tersier;
    }

    public void setSaluran_tersier(float saluran_tersier) {
        this.saluran_tersier = saluran_tersier;
    }

    public float getJalan_saluran_tidak_ada_ripper() {
        return jalan_saluran_tidak_ada_ripper;
    }

    public void setJalan_saluran_tidak_ada_ripper(float jalan_saluran_tidak_ada_ripper) {
        this.jalan_saluran_tidak_ada_ripper = jalan_saluran_tidak_ada_ripper;
    }

    public float getSaluran_tersier_examini() {
        return saluran_tersier_examini;
    }

    public void setSaluran_tersier_examini(float saluran_tersier_examini) {
        this.saluran_tersier_examini = saluran_tersier_examini;
    }

    public float getSaluran_tersier_traktor() {
        return saluran_tersier_traktor;
    }

    public void setSaluran_tersier_traktor(float saluran_tersier_traktor) {
        this.saluran_tersier_traktor = saluran_tersier_traktor;
    }

    public int getJumlah_saluran_tersier() {
        return jumlah_saluran_tersier;
    }

    public void setJumlah_saluran_tersier(int jumlah_saluran_tersier) {
        this.jumlah_saluran_tersier = jumlah_saluran_tersier;
    }

    public float getLebar_tersier_dinding_atas() {
        return lebar_tersier_dinding_atas;
    }

    public void setLebar_tersier_dinding_atas(float lebar_tersier_dinding_atas) {
        this.lebar_tersier_dinding_atas = lebar_tersier_dinding_atas;
    }

    public float getLebar_tersier_erosi() {
        return lebar_tersier_erosi;
    }

    public void setLebar_tersier_erosi(float lebar_tersier_erosi) {
        this.lebar_tersier_erosi = lebar_tersier_erosi;
    }

    public float getLebar_sekunder_dinding_atas() {
        return lebar_sekunder_dinding_atas;
    }

    public void setLebar_sekunder_dinding_atas(float lebar_sekunder_dinding_atas) {
        this.lebar_sekunder_dinding_atas = lebar_sekunder_dinding_atas;
    }

    public float getLebar_sekunder_erosi() {
        return lebar_sekunder_erosi;
    }

    public void setLebar_sekunder_erosi(float lebar_sekunder_erosi) {
        this.lebar_sekunder_erosi = lebar_sekunder_erosi;
    }

    public float getJarak_antar_poros_gulud() {
        return jarak_antar_poros_gulud;
    }

    public void setJarak_antar_poros_gulud(float jarak_antar_poros_gulud) {
        this.jarak_antar_poros_gulud = jarak_antar_poros_gulud;
    }

    public float getKedalaman_kuku_ridger() {
        return kedalaman_kuku_ridger;
    }

    public void setKedalaman_kuku_ridger(float kedalaman_kuku_ridger) {
        this.kedalaman_kuku_ridger = kedalaman_kuku_ridger;
    }

    public String getLolos_ayakan() {
        return lolos_ayakan;
    }

    public void setLolos_ayakan(String lolos_ayakan) {
        this.lolos_ayakan = lolos_ayakan;
    }

    public String getTidak_lolos_ayakan() {
        return tidak_lolos_ayakan;
    }

    public void setTidak_lolos_ayakan(String tidak_lolos_ayakan) {
        this.tidak_lolos_ayakan = tidak_lolos_ayakan;
    }

    // Getters and Setters
    public Double getAplikasiRapat() {
        return aplikasiRapat;
    }

    public void setAplikasiRapat(Double aplikasiRapat) {
        this.aplikasiRapat = aplikasiRapat;
    }

    public Double getBonggolTerpecah() {
        return bonggolTerpecah;
    }

    public void setBonggolTerpecah(Double bonggolTerpecah) {
        this.bonggolTerpecah = bonggolTerpecah;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public Double getTanamanHancur() {
        return tanamanHancur;
    }

    public void setTanamanHancur(Double tanamanHancur) {
        this.tanamanHancur = tanamanHancur;
    }

    public String getJenisImplement() {
        return jenisImplement;
    }

    public void setJenisImplement(String jenisImplement) {
        this.jenisImplement = jenisImplement;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }


    public String getNoSpk() {
        return noSpk;
    }

    public void setNoSpk(String noSpk) {
        this.noSpk = noSpk;
    }

    public String getNoUnitImplement() {
        return noUnitImplement;
    }

    public void setNoUnitImplement(String noUnitImplement) {
        this.noUnitImplement = noUnitImplement;
    }

    public String getStatusPengamatan() {
        return statusPengamatan;
    }

    public void setStatusPengamatan(String statusPengamatan) {
        this.statusPengamatan = statusPengamatan;
    }

    public String getWil() {
        return wil;
    }

    public void setWil(String wil) {
        this.wil = wil;
    }


    public String getComodity_bajak() {
        return comodity_bajak;
    }

    public void setComodity_bajak(String comodity_bajak) {
        this.comodity_bajak = comodity_bajak;
    }

    public String getAplikasi_pinggiran() {
        return aplikasi_pinggiran;
    }

    public void setAplikasi_pinggiran(String aplikasi_pinggiran) {
        this.aplikasi_pinggiran = aplikasi_pinggiran;
    }

    public String getKerataan_aplikasi() {
        return kerataan_aplikasi;
    }

    public void setKerataan_aplikasi(String kerataan_aplikasi) {
        this.kerataan_aplikasi = kerataan_aplikasi;
    }

    public String getStd_musim() {
        return std_musim;
    }

    public void setStd_musim(String std_musim) {
        this.std_musim = std_musim;
    }

    public String getStd_jenisbajak() {
        return std_jenisbajak;
    }

    public void setStd_jenisbajak(String std_jenisbajak) {
        this.std_jenisbajak = std_jenisbajak;
    }

    public int getNo_sample() {
        return no_sample;
    }

    public void setNo_sample(int no_sample) {
        this.no_sample = no_sample;
    }

    public Float getLuas_plot() {
        return luas_plot;
    }

    public void setLuas_plot(Float luas_plot) {
        this.luas_plot = luas_plot;
    }

    public Float getKEDALAMAN() {
        return KEDALAMAN;
    }

    public void setKEDALAMAN(Float KEDALAMAN) {
        this.KEDALAMAN = KEDALAMAN;
    }

    public Float getDEAD_FURROW() {
        return DEAD_FURROW;
    }

    public void setDEAD_FURROW(Float DEAD_FURROW) {
        this.DEAD_FURROW = DEAD_FURROW;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }
}
