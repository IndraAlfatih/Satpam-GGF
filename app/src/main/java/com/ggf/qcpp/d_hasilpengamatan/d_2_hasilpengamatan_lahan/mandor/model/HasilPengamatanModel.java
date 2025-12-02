package com.ggf.qcpp.d_hasilpengamatan.d_2_hasilpengamatan_lahan.mandor.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class HasilPengamatanModel implements Serializable {
    @SerializedName("id")
    private String id;
    @SerializedName("plot")
    private String plot;
    @SerializedName("lokasi_adukan")
    public String lokasi_adukan;
    @SerializedName("lokasi")
    private String lokasi;
    @SerializedName("no_spk")
    private String no_spk;

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

    @SerializedName("rencana_panen")
    public String rencana_panen;


    @SerializedName("aplikasi_rapat")
    private String aplikasi_rapat;

    @SerializedName("bonggol_terpecah")
    private String bonggol_terpecah;

    @SerializedName("tanaman_hancur")
    private String tanaman_hancur;

    @SerializedName("no_sample")
    private String no_sample;

    @SerializedName("created_at")
    private String created_at;

    @SerializedName("verify_mandor")
    private int verify_mandor;
    @SerializedName("verify_kasi")
    private int verify_kasi;
    @SerializedName("verify_kabag")
    private int verify_kabag;

    @SerializedName("no_spk2")
    public String no_spk2;
    @SerializedName("no_line")
    public String no_line;

    @SerializedName("jenis_implement")
    public String jenis_implement;

  //model Bajak
//    @SerializedName("lebar_plot")
//    public int LEBAR_PLOT;

    @SerializedName("kedalaman")
    public int KEDALAMAN;

    @SerializedName("dead_furrow")
    public String DEAD_FURROW;

    @SerializedName("aplikasi_pinggiran")
    public String APLIKASI_PINGGIRAN;
    @SerializedName("kerataan_aplikasi")
    public String APLIKASI_KERATAAN;

    @SerializedName("jumlah_sample")
    public int jumlah_sample;



    @SerializedName("lolos_ayakan")
    public String lolos_ayakan;

    @SerializedName("tidak_lolos_ayakan")
    public String tidak_lolos_ayakan;


    @SerializedName("jarak_antar_poros_gulud")
    public String JARAK_ANTAR_POROS_GULUD;

    @SerializedName("kedalaman_kuku_ridge")
    public String KEDALAMAN_KUKU_RIDGER;

    @SerializedName("analisa_a1")
    public float ANALISA_A1;


    @SerializedName("analisa_a2")
    public float ANALISA_A2;

    @SerializedName("analisa_a3")
    public float ANALISA_A3;

    @SerializedName("analisa_a4")
    public float ANALISA_A4;
    @SerializedName("luas_plot")
    public String LUAS_PLOT;

//batas model bajak

    //jalan Saluran Model
    @SerializedName("plot360")
    public int PLOT360;
    @SerializedName("block")
    public int BLOCK;
    @SerializedName("seksi")
    public int SEKSI;
    @SerializedName("perimeter")
    public int PERIMETER;
    @SerializedName("sekunder")
    public int SEKUNDER;
    @SerializedName("tersier")
    public int TERSIER;
    @SerializedName("jalan_saluran_tidakadaripper")
    public int JALAN_SALURAN_RIPPER;
    /*                           */

    @SerializedName("estimasi")
    public int ESTIMASI;

    @SerializedName("bonggol_segar_kurang_dari")
    public int BONGGOL_SEGAR_KURANG_DARI;

    @SerializedName("bonggol_segar_lebih_dari")
    public int BONGGOL_SEGAR_LEBIH_DARI;




    //petik bibit

    @SerializedName("label")
    public String label;

    @SerializedName("jenis_bibit1")
    public String jenis_bibit1;

    @SerializedName("nomor_bibit1")
    public String nomor_bibit1;

    @SerializedName("nomor_bibit")
    public String nomor_bibit;



    @SerializedName("jenis_unit")
    public String jenis_unit;

    @SerializedName("selisih")
    public String selisih;
    @SerializedName("jenis_bibit")
    public String jenis_bibit;

    @SerializedName("kelas_bibit")
    public String kelas_bibit;
    @SerializedName("bibit_normal")
    public float bibit_normal;

    @SerializedName("bibit_afkir")
    public float bibit_afkir;

    @SerializedName("real")
    public float real;

    @SerializedName("bibit_over_plus")
    public int bibit_over_plus;

    @SerializedName("tidak_crown")
    public int tidak_crown;

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

    @SerializedName("jenis_bajak")
    public String jenis_bajak;

    @SerializedName("bibit_6")
    public int bibit_6;

    @SerializedName("bibit_7")
    public int bibit_7;
    @SerializedName("jumlah_bibit_tertumpuk")
    public int jumlah_bibit_tertumpuk;

    @SerializedName("asal_do")
    public String asal_do;

    @SerializedName("tujuan_do")
    public String tujuan_do;

    @SerializedName("no_kendaraan")
    public String no_kendaraan;

    @SerializedName("informasi_bibit_terdipping")
    public String informasi_bibit_terdipping;



//batas petik bibit


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

    public String getLokasi_adukan() {
        return lokasi_adukan;
    }

    public void setLokasi_adukan(String lokasi_adukan) {
        this.lokasi_adukan = lokasi_adukan;
    }


    //jumlahBaris


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

    public String getNomor_bibit1() {
        return nomor_bibit1;
    }

    public void setNomor_bibit1(String nomor_bibit1) {
        this.nomor_bibit1 = nomor_bibit1;
    }

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

    @SerializedName("jumlah_baris")
    public int jumlah_baris;

    @SerializedName("jumlah_baris_std")
    public int jumlah_baris_std;

    @SerializedName("penambahan_baris")
    public int penambahan_baris;

    @SerializedName("jumlah_pb")
    public int jumlah_pb;

//    @SerializedName("tersier")
//    public int tersier;
    @SerializedName("penambahan_baris_sal_tersier")
    public int penambahan_baris_sal_tersier;

    @SerializedName("kancingan")
    public int kancingan;

    @SerializedName("hasil")
    public float hasil;

    //transport
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

    //tanam
    @SerializedName("std_musim")
    public String std_musim;

    @SerializedName("status_pengamatan")
    public String status_pengamatan;
    @SerializedName("mandor_bibit")
    public String mandor_bibit;
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
    @SerializedName("tot_bibittercecer")
    public int tot_bibittercecer;
    @SerializedName("tot_tidaktegakterinjak")
    public int tot_tidaktegakterinjak;
    @SerializedName("tot_tegakterinjak")
    public int tot_tegakterinjak;


    @SerializedName("netto")
    private String netto;

    @SerializedName("tanggal_panen_rampet")
    private String tanggal_panen_rampet;

    @SerializedName("status_rc")
    private String status_rc;

    @SerializedName("wil")
    private String wil;

    @SerializedName("ketinggian_sampah")
    private float ketinggian_sampah;

    @SerializedName("jumlah_sample_masuk_standar_kebersihan_kupasan")
    private float jumlah_sample_masuk_standar_kebersihan_kupasan;

    @SerializedName("jumlah_sample_masuk_standar_potongan_bonggol")
    private float jumlah_sample_masuk_standar_potongan_bonggol;

    @SerializedName("rerata_panjang_bonggol")
    private float rerata_panjang_bonggol;

    @SerializedName("jumlah_sample_masuk_standar_kondisi_bonggol")
    private float jumlah_sample_masuk_standar_kondisi_bonggol;

    @SerializedName("jumlah_sample_masuk_standar_kondisi_bin")
    private float jumlah_sample_masuk_standar_kondisi_bin;

    @SerializedName("kondisimuatan")
    private float kondisimuatan;

    @SerializedName("panjang_pengamatan")
    private int panjang_pengamatan;

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

    @SerializedName("keterangan")
    public String keterangan;

    @SerializedName("kode_unit")
    public String kodeUnit;

    @SerializedName("jumlah_bibit_ikat_di_spk")
    public int jumlahBibitIkatDiSpk;

    @SerializedName("real_bibit_ikat")
    public int realBibitIkat;


    @SerializedName("jumlah_keliling_batang_masuk_standar")
    public int jumlahKelilingBatangMasukStandar;

    @SerializedName("keliling_bibit_atas_1")
    public float kelilingBibitAtas1; // Nullable

    @SerializedName("keliling_bibit_atas_2")
    public float kelilingBibitAtas2; // Nullable

    @SerializedName("keliling_bibit_atas_3")
    public float kelilingBibitAtas3; // Nullable

    @SerializedName("keliling_bibit_atas_4")
    public float kelilingBibitAtas4; // Nullable

    @SerializedName("keliling_bibit_atas_5")
    public float kelilingBibitAtas5; // Nullable

    @SerializedName("keliling_bibit_atas_6")
    public float kelilingBibitAtas6; // Nullable

    @SerializedName("keliling_bibit_atas_7")
    public float kelilingBibitAtas7; // Nullable

    @SerializedName("keliling_bibit_atas_8")
    public float kelilingBibitAtas8; // Nullable

    @SerializedName("keliling_bibit_atas_9")
    public float kelilingBibitAtas9; // Nullable

    @SerializedName("keliling_bibit_atas_10")
    public float kelilingBibitAtas10; // Nullable

    @SerializedName("keliling_bibit_bawah_1")
    public float kelilingBibitBawah1; // Nullable

    @SerializedName("keliling_bibit_bawah_2")
    public float kelilingBibitBawah2; // Nullable

    @SerializedName("keliling_bibit_bawah_3")
    public float kelilingBibitBawah3; // Nullable

    @SerializedName("keliling_bibit_bawah_4")
    public float kelilingBibitBawah4; // Nullable

    @SerializedName("keliling_bibit_bawah_5")
    public float kelilingBibitBawah5; // Nullable

    @SerializedName("keliling_bibit_bawah_6")
    public float kelilingBibitBawah6; // Nullable

    @SerializedName("keliling_bibit_bawah_7")
    public float kelilingBibitBawah7; // Nullable

    @SerializedName("keliling_bibit_bawah_8")
    public float kelilingBibitBawah8; // Nullable

    @SerializedName("keliling_bibit_bawah_9")
    public float kelilingBibitBawah9; // Nullable

    @SerializedName("keliling_bibit_bawah_10")
    public float kelilingBibitBawah10; // Nullable

    @SerializedName("jumlah_bibit_di_spk")
    public int jumlahBibitDiSpk;

    @SerializedName("tanggal_pengamatan")
    public String tanggal_pengamatan;

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

    @SerializedName("jenis_bahan_1")
    public String jenis_bahan_1;

    @SerializedName("rencana_1")
    public float rencana_1;

    @SerializedName("real_1")
    public float real_1;

    @SerializedName("jenis_bahan_2")
    public String jenis_bahan_2;

    @SerializedName("rencana_2")
    public float rencana_2;

    @SerializedName("real_2")
    public float real_2;

    @SerializedName("jenis_bahan_3")
    public String jenis_bahan_3;

    @SerializedName("rencana_3")
    public float rencana_3;

    @SerializedName("real_3")
    public float real_3;

    @SerializedName("jenis_bahan_4")
    public String jenis_bahan_4;

    @SerializedName("rencana_4")
    public float rencana_4;

    @SerializedName("real_4")
    public float real_4;

    @SerializedName("jenis_bahan_5")
    public String jenis_bahan_5;

    @SerializedName("rencana_5")
    public float rencana_5;

    @SerializedName("real_5")
    public float real_5;

    @SerializedName("jenis_bahan_6")
    public String jenis_bahan_6;

    @SerializedName("rencana_6")
    public float rencana_6;

    @SerializedName("real_6")
    public float real_6;

    @SerializedName("jenis_bahan_7")
    public String jenis_bahan_7;

    @SerializedName("rencana_7")
    public float rencana_7;

    @SerializedName("real_7")
    public float real_7;

    @SerializedName("jenis_bahan_8")
    public String jenis_bahan_8;

    @SerializedName("rencana_8")
    public float rencana_8;

    @SerializedName("real_8")
    public float real_8;

    @SerializedName("jenis_bahan_9")
    public String jenis_bahan_9;

    @SerializedName("rencana_9")
    public float rencana_9;

    @SerializedName("real_9")
    public float real_9;

    @SerializedName("jenis_bahan_10")
    public String jenis_bahan_10;

    @SerializedName("rencana_10")
    public float rencana_10;

    @SerializedName("real_10")
    public float real_10;


    @SerializedName("pengisian_ke")
    public float pengisian_ke;

    @SerializedName("volume_air")
    public float volume_air;

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

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
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

    public String getRencana_panen() {
        return rencana_panen;
    }

    public void setRencana_panen(String rencana_panen) {
        this.rencana_panen = rencana_panen;
    }

    public String getJenis_bahan_1() {
        return jenis_bahan_1;
    }

    public void setJenis_bahan_1(String jenis_bahan_1) {
        this.jenis_bahan_1 = jenis_bahan_1;
    }

    public float getRencana_1() {
        return rencana_1;
    }

    public void setRencana_1(float rencana_1) {
        this.rencana_1 = rencana_1;
    }

    public float getReal_1() {
        return real_1;
    }

    public void setReal_1(float real_1) {
        this.real_1 = real_1;
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

    @SerializedName("tanggal_panen")
    public String tanggal_panen;

    @SerializedName("regu_panen")
    public String regu_panen;


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

    public String getJenis_implement() {
        return jenis_implement;
    }

    public void setJenis_implement(String jenis_implement) {
        this.jenis_implement = jenis_implement;
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

    public int getPanjang_pengamatan() {
        return panjang_pengamatan;
    }

    public void setPanjang_pengamatan(int panjang_pengamatan) {
        this.panjang_pengamatan = panjang_pengamatan;
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

    public String getPlotgetPlot_teramati() {
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

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getKodeUnit() {
        return kodeUnit;
    }

    public void setKodeUnit(String kodeUnit) {
        this.kodeUnit = kodeUnit;
    }

    public int getJumlahBibitIkatDiSpk() {
        return jumlahBibitIkatDiSpk;
    }

    public void setJumlahBibitIkatDiSpk(int jumlahBibitIkatDiSpk) {
        this.jumlahBibitIkatDiSpk = jumlahBibitIkatDiSpk;
    }

    public int getRealBibitIkat() {
        return realBibitIkat;
    }

    public void setRealBibitIkat(int realBibitIkat) {
        this.realBibitIkat = realBibitIkat;
    }





    public int getJumlahKelilingBatangMasukStandar() {
        return jumlahKelilingBatangMasukStandar;
    }

    public void setJumlahKelilingBatangMasukStandar(int jumlahKelilingBatangMasukStandar) {
        this.jumlahKelilingBatangMasukStandar = jumlahKelilingBatangMasukStandar;
    }

    public float getKelilingBibitAtas1() {
        return kelilingBibitAtas1;
    }

    public void setKelilingBibitAtas1(float kelilingBibitAtas1) {
        this.kelilingBibitAtas1 = kelilingBibitAtas1;
    }

    public float getKelilingBibitAtas2() {
        return kelilingBibitAtas2;
    }

    public void setKelilingBibitAtas2(float kelilingBibitAtas2) {
        this.kelilingBibitAtas2 = kelilingBibitAtas2;
    }

    public float getKelilingBibitAtas3() {
        return kelilingBibitAtas3;
    }

    public void setKelilingBibitAtas3(float kelilingBibitAtas3) {
        this.kelilingBibitAtas3 = kelilingBibitAtas3;
    }

    public float getKelilingBibitAtas4() {
        return kelilingBibitAtas4;
    }

    public void setKelilingBibitAtas4(float kelilingBibitAtas4) {
        this.kelilingBibitAtas4 = kelilingBibitAtas4;
    }

    public float getKelilingBibitAtas5() {
        return kelilingBibitAtas5;
    }

    public void setKelilingBibitAtas5(float kelilingBibitAtas5) {
        this.kelilingBibitAtas5 = kelilingBibitAtas5;
    }

    public float getKelilingBibitAtas6() {
        return kelilingBibitAtas6;
    }

    public void setKelilingBibitAtas6(float kelilingBibitAtas6) {
        this.kelilingBibitAtas6 = kelilingBibitAtas6;
    }

    public float getKelilingBibitAtas7() {
        return kelilingBibitAtas7;
    }

    public void setKelilingBibitAtas7(float kelilingBibitAtas7) {
        this.kelilingBibitAtas7 = kelilingBibitAtas7;
    }

    public float getKelilingBibitAtas8() {
        return kelilingBibitAtas8;
    }

    public void setKelilingBibitAtas8(float kelilingBibitAtas8) {
        this.kelilingBibitAtas8 = kelilingBibitAtas8;
    }

    public float getKelilingBibitAtas9() {
        return kelilingBibitAtas9;
    }

    public void setKelilingBibitAtas9(float kelilingBibitAtas9) {
        this.kelilingBibitAtas9 = kelilingBibitAtas9;
    }

    public float getKelilingBibitAtas10() {
        return kelilingBibitAtas10;
    }

    public void setKelilingBibitAtas10(float kelilingBibitAtas10) {
        this.kelilingBibitAtas10 = kelilingBibitAtas10;
    }

    public float getKelilingBibitBawah1() {
        return kelilingBibitBawah1;
    }

    public void setKelilingBibitBawah1(float kelilingBibitBawah1) {
        this.kelilingBibitBawah1 = kelilingBibitBawah1;
    }

    public float getKelilingBibitBawah2() {
        return kelilingBibitBawah2;
    }

    public void setKelilingBibitBawah2(float kelilingBibitBawah2) {
        this.kelilingBibitBawah2 = kelilingBibitBawah2;
    }

    public float getKelilingBibitBawah3() {
        return kelilingBibitBawah3;
    }

    public void setKelilingBibitBawah3(float kelilingBibitBawah3) {
        this.kelilingBibitBawah3 = kelilingBibitBawah3;
    }

    public float getKelilingBibitBawah4() {
        return kelilingBibitBawah4;
    }

    public void setKelilingBibitBawah4(float kelilingBibitBawah4) {
        this.kelilingBibitBawah4 = kelilingBibitBawah4;
    }

    public float getKelilingBibitBawah5() {
        return kelilingBibitBawah5;
    }

    public void setKelilingBibitBawah5(float kelilingBibitBawah5) {
        this.kelilingBibitBawah5 = kelilingBibitBawah5;
    }

    public float getKelilingBibitBawah6() {
        return kelilingBibitBawah6;
    }

    public void setKelilingBibitBawah6(float kelilingBibitBawah6) {
        this.kelilingBibitBawah6 = kelilingBibitBawah6;
    }

    public float getKelilingBibitBawah7() {
        return kelilingBibitBawah7;
    }

    public void setKelilingBibitBawah7(float kelilingBibitBawah7) {
        this.kelilingBibitBawah7 = kelilingBibitBawah7;
    }

    public float getKelilingBibitBawah8() {
        return kelilingBibitBawah8;
    }

    public void setKelilingBibitBawah8(float kelilingBibitBawah8) {
        this.kelilingBibitBawah8 = kelilingBibitBawah8;
    }

    public float getKelilingBibitBawah9() {
        return kelilingBibitBawah9;
    }

    public void setKelilingBibitBawah9(float kelilingBibitBawah9) {
        this.kelilingBibitBawah9 = kelilingBibitBawah9;
    }

    public float getKelilingBibitBawah10() {
        return kelilingBibitBawah10;
    }

    public void setKelilingBibitBawah10(float kelilingBibitBawah10) {
        this.kelilingBibitBawah10 = kelilingBibitBawah10;
    }

    public int getJumlahBibitDiSpk() {
        return jumlahBibitDiSpk;
    }

    public void setJumlahBibitDiSpk(int jumlahBibitDiSpk) {
        this.jumlahBibitDiSpk = jumlahBibitDiSpk;
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

    public String getNetto() {
        return netto;
    }

    public void setNetto(String netto) {
        this.netto = netto;
    }

    public String getTanggal_panen_rampet() {
        return tanggal_panen_rampet;
    }

    public void setTanggal_panen_rampet(String tanggal_panen_rampet) {
        this.tanggal_panen_rampet = tanggal_panen_rampet;
    }

    public String getStatus_rc() {
        return status_rc;
    }

    public void setStatus_rc(String status_rc) {
        this.status_rc = status_rc;
    }

    public String getWil() {
        return wil;
    }

    public void setWil(String wil) {
        this.wil = wil;
    }

    public float getKetinggian_sampah() {
        return ketinggian_sampah;
    }

    public void setKetinggian_sampah(float ketinggian_sampah) {
        this.ketinggian_sampah = ketinggian_sampah;
    }

    public float getJumlah_sample_masuk_standar_kebersihan_kupasan() {
        return jumlah_sample_masuk_standar_kebersihan_kupasan;
    }

    public void setJumlah_sample_masuk_standar_kebersihan_kupasan(float jumlah_sample_masuk_standar_kebersihan_kupasan) {
        this.jumlah_sample_masuk_standar_kebersihan_kupasan = jumlah_sample_masuk_standar_kebersihan_kupasan;
    }

    public float getJumlah_sample_masuk_standar_potongan_bonggol() {
        return jumlah_sample_masuk_standar_potongan_bonggol;
    }

    public void setJumlah_sample_masuk_standar_potongan_bonggol(float jumlah_sample_masuk_standar_potongan_bonggol) {
        this.jumlah_sample_masuk_standar_potongan_bonggol = jumlah_sample_masuk_standar_potongan_bonggol;
    }

    public float getRerata_panjang_bonggol() {
        return rerata_panjang_bonggol;
    }

    public void setRerata_panjang_bonggol(float rerata_panjang_bonggol) {
        this.rerata_panjang_bonggol = rerata_panjang_bonggol;
    }

    public float getJumlah_sample_masuk_standar_kondisi_bonggol() {
        return jumlah_sample_masuk_standar_kondisi_bonggol;
    }

    public void setJumlah_sample_masuk_standar_kondisi_bonggol(float jumlah_sample_masuk_standar_kondisi_bonggol) {
        this.jumlah_sample_masuk_standar_kondisi_bonggol = jumlah_sample_masuk_standar_kondisi_bonggol;
    }

    public float getJumlah_sample_masuk_standar_kondisi_bin() {
        return jumlah_sample_masuk_standar_kondisi_bin;
    }

    public void setJumlah_sample_masuk_standar_kondisi_bin(float jumlah_sample_masuk_standar_kondisi_bin) {
        this.jumlah_sample_masuk_standar_kondisi_bin = jumlah_sample_masuk_standar_kondisi_bin;
    }

    public float getKondisimuatan() {
        return kondisimuatan;
    }

    public void setKondisimuatan(float kondisimuatan) {
        this.kondisimuatan = kondisimuatan;
    }

    public String getStd_musim() {
        return std_musim;
    }

    public void setStd_musim(String std_musim) {
        this.std_musim = std_musim;
    }

    public String getStatus_pengamatan() {
        return status_pengamatan;
    }

    public void setStatus_pengamatan(String status_pengamatan) {
        this.status_pengamatan = status_pengamatan;
    }

    public String getMandor_bibit() {
        return mandor_bibit;
    }

    public void setMandor_bibit(String mandor_bibit) {
        this.mandor_bibit = mandor_bibit;
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

    public String getJenis_bajak() {
        return jenis_bajak;
    }

    public void setJenis_bajak(String jenis_bajak) {
        this.jenis_bajak = jenis_bajak;
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

    public int getJumlah_baris() {
        return jumlah_baris;
    }

    public void setJumlah_baris(int jumlah_baris) {
        this.jumlah_baris = jumlah_baris;
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

    public float getHasil() {
        return hasil;
    }

    public void setHasil(float hasil) {
        this.hasil = hasil;
    }

    public String getInformasi_bibit_terdipping() {
        return informasi_bibit_terdipping;
    }

    public void setInformasi_bibit_terdipping(String informasi_bibit_terdipping) {
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

    public String getNo_kendaraan() {
        return no_kendaraan;
    }

    public void setNo_kendaraan(String no_kendaraan) {
        this.no_kendaraan = no_kendaraan;
    }

    public String getJenis_bibit1() {
        return jenis_bibit1;
    }

    public void setJenis_bibit1(String jenis_bibit1) {
        this.jenis_bibit1 = jenis_bibit1;
    }

    public int getJumlah_bibit_tertumpuk() {
        return jumlah_bibit_tertumpuk;
    }

    public void setJumlah_bibit_tertumpuk(int jumlah_bibit_tertumpuk) {
        this.jumlah_bibit_tertumpuk = jumlah_bibit_tertumpuk;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getSelisih() {
        return selisih;
    }

    public void setSelisih(String selisih) {
        this.selisih = selisih;
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

    public float getReal() {
        return real;
    }

    public void setReal(float real) {
        this.real = real;
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

//    public String getLOLOS_AYAKAN() {
//        return LOLOS_AYAKAN;
//    }
//
//    public void setLOLOS_AYAKAN(String LOLOS_AYAKAN) {
//        this.LOLOS_AYAKAN = LOLOS_AYAKAN;
//    }
//
//    public String getTIDAK_LOLOS_AYAKAN() {
//        return TIDAK_LOLOS_AYAKAN;
//    }
//
//    public void setTIDAK_LOLOS_AYAKAN(String TIDAK_LOLOS_AYAKAN) {
//        this.TIDAK_LOLOS_AYAKAN = TIDAK_LOLOS_AYAKAN;
//    }




    public String getJARAK_ANTAR_POROS_GULUD() {
        return JARAK_ANTAR_POROS_GULUD;
    }

    public void setJARAK_ANTAR_POROS_GULUD(String JARAK_ANTAR_POROS_GULUD) {
        this.JARAK_ANTAR_POROS_GULUD = JARAK_ANTAR_POROS_GULUD;
    }

    public String getKEDALAMAN_KUKU_RIDGER() {
        return KEDALAMAN_KUKU_RIDGER;
    }

    public void setKEDALAMAN_KUKU_RIDGER(String KEDALAMAN_KUKU_RIDGER) {
        this.KEDALAMAN_KUKU_RIDGER = KEDALAMAN_KUKU_RIDGER;
    }

    public float getANALISA_A1() {
        return ANALISA_A1;
    }

    public void setANALISA_A1(float ANALISA_A1) {
        this.ANALISA_A1 = ANALISA_A1;
    }

    public float getANALISA_A2() {
        return ANALISA_A2;
    }

    public void setANALISA_A2(float ANALISA_A2) {
        this.ANALISA_A2 = ANALISA_A2;
    }

    public float getANALISA_A3() {
        return ANALISA_A3;
    }

    public void setANALISA_A3(float ANALISA_A3) {
        this.ANALISA_A3 = ANALISA_A3;
    }

    public float getANALISA_A4() {
        return ANALISA_A4;
    }

    public void setANALISA_A4(float ANALISA_A4) {
        this.ANALISA_A4 = ANALISA_A4;
    }

    public String getLUAS_PLOT() {
        return LUAS_PLOT;
    }

    public void setLUAS_PLOT(String LUAS_PLOT) {
        this.LUAS_PLOT = LUAS_PLOT;
    }

    public int getPLOT360() {
        return PLOT360;
    }

    public void setPLOT360(int PLOT360) {
        this.PLOT360 = PLOT360;
    }

    public int getBLOCK() {
        return BLOCK;
    }

    public void setBLOCK(int BLOCK) {
        this.BLOCK = BLOCK;
    }

    public int getSEKSI() {
        return SEKSI;
    }

    public void setSEKSI(int SEKSI) {
        this.SEKSI = SEKSI;
    }

    public int getPERIMETER() {
        return PERIMETER;
    }

    public void setPERIMETER(int PERIMETER) {
        this.PERIMETER = PERIMETER;
    }

    public int getSEKUNDER() {
        return SEKUNDER;
    }

    public void setSEKUNDER(int SEKUNDER) {
        this.SEKUNDER = SEKUNDER;
    }

    public int getTERSIER() {
        return TERSIER;
    }

    public void setTERSIER(int TERSIER) {
        this.TERSIER = TERSIER;
    }

    public int getJALAN_SALURAN_RIPPER() {
        return JALAN_SALURAN_RIPPER;
    }

    public void setJALAN_SALURAN_RIPPER(int JALAN_SALURAN_RIPPER) {
        this.JALAN_SALURAN_RIPPER = JALAN_SALURAN_RIPPER;
    }

    public int getESTIMASI() {
        return ESTIMASI;
    }

    public void setESTIMASI(int ESTIMASI) {
        this.ESTIMASI = ESTIMASI;
    }

    public int getBONGGOL_SEGAR_KURANG_DARI() {
        return BONGGOL_SEGAR_KURANG_DARI;
    }

    public void setBONGGOL_SEGAR_KURANG_DARI(int BONGGOL_SEGAR_KURANG_DARI) {
        this.BONGGOL_SEGAR_KURANG_DARI = BONGGOL_SEGAR_KURANG_DARI;
    }

    public int getBONGGOL_SEGAR_LEBIH_DARI() {
        return BONGGOL_SEGAR_LEBIH_DARI;
    }

    public void setBONGGOL_SEGAR_LEBIH_DARI(int BONGGOL_SEGAR_LEBIH_DARI) {
        this.BONGGOL_SEGAR_LEBIH_DARI = BONGGOL_SEGAR_LEBIH_DARI;
    }


    public int getKEDALAMAN() {
        return KEDALAMAN;
    }

    public void setKEDALAMAN(int KEDALAMAN) {
        this.KEDALAMAN = KEDALAMAN;
    }

    public String getDEAD_FURROW() {
        return DEAD_FURROW;
    }

    public void setDEAD_FURROW(String DEAD_FURROW) {
        this.DEAD_FURROW = DEAD_FURROW;
    }

    //


    public String getAPLIKASI_PINGGIRAN() {
        return APLIKASI_PINGGIRAN;
    }

    public void setAPLIKASI_PINGGIRAN(String APLIKASI_PINGGIRAN) {
        this.APLIKASI_PINGGIRAN = APLIKASI_PINGGIRAN;
    }

    public String getAPLIKASI_KERATAAN() {
        return APLIKASI_KERATAAN;
    }

    public void setAPLIKASI_KERATAAN(String APLIKASI_KERATAAN) {
        this.APLIKASI_KERATAAN = APLIKASI_KERATAAN;
    }

    public int getVerify_mandor() {
        return verify_mandor;
    }

    public void setVerify_mandor(int verify_mandor) {
        this.verify_mandor = verify_mandor;
    }

    public int getVerify_kasi() {
        return verify_kasi;
    }

    public void setVerify_kasi(int verify_kasi) {
        this.verify_kasi = verify_kasi;
    }

    public int getVerify_kabag() {
        return verify_kabag;
    }

    public void setVerify_kabag(int verify_kabag) {
        this.verify_kabag = verify_kabag;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNo_spk() {
        return no_spk;
    }

    public void setNo_spk(String no_spk) {
        this.no_spk = no_spk;
    }

    public String getAplikasi_rapat() {
        return aplikasi_rapat;
    }

    public void setAplikasi_rapat(String aplikasi_rapat) {
        this.aplikasi_rapat = aplikasi_rapat;
    }

    public String getBonggol_terpecah() {
        return bonggol_terpecah;
    }

    public void setBonggol_terpecah(String bonggol_terpecah) {
        this.bonggol_terpecah = bonggol_terpecah;
    }

    public String getTanaman_hancur() {
        return tanaman_hancur;
    }

    public void setTanaman_hancur(String tanaman_hancur) {
        this.tanaman_hancur = tanaman_hancur;
    }

    public String getNo_sample() {
        return no_sample;
    }

    public void setNo_sample(String no_sample) {
        this.no_sample = no_sample;
    }



    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getJenis_unit() {
        return jenis_unit;
    }

    public void setJenis_unit(String jenis_unit) {
        this.jenis_unit = jenis_unit;
    }



    public int getJumlah_sample() {
        return jumlah_sample;
    }

    public void setJumlah_sample(int jumlah_sample) {
        this.jumlah_sample = jumlah_sample;
    }

    public int getTot_bibittercecer() {
        return tot_bibittercecer;
    }

    public void setTot_bibittercecer(int tot_bibittercecer) {
        this.tot_bibittercecer = tot_bibittercecer;
    }
}
