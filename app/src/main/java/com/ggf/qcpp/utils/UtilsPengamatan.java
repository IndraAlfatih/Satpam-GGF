package com.ggf.qcpp.utils;

import com.ggf.qcpp.e_formpengamatan.chopper.model.PlotModel;
import com.ggf.qcpp.e_formpengamatan.chopper.model.SampleModel;

import java.util.List;

public class UtilsPengamatan {

    /**
     * Cek apakah sample dengan kombinasi PLOT + NoSample sudah ada
     */
    public static boolean isSampleExist(List<PlotModel> dataPlot, SampleModel sample) {
        if (dataPlot == null || sample == null) return false;

        for (PlotModel plot : dataPlot) {
            if (String.valueOf(plot.getPLOT()).equals(String.valueOf(sample.getPLOT()))) {
                for (SampleModel s : plot.getSAMPLE()) {
                    if (s.getNo_sample() == sample.getNo_sample()) {
                        return true; // Data duplicate ditemukan
                    }
                }
            }
        }
        return false;
    }

    /**
     * Tambahkan sample jika belum ada, return true kalau sukses
     */
    public static boolean addSampleIfNotExist(List<PlotModel> dataPlot, SampleModel sample) {
        if (isSampleExist(dataPlot, sample)) {
            return false; // duplikat, tidak ditambahkan
        }

        // cari plot existing
        for (PlotModel plot : dataPlot) {
            if (String.valueOf(plot.getPLOT()).equals(String.valueOf(sample.getPLOT()))) {
                plot.getSAMPLE().add(sample);
                return true;
            }
        }

        // kalau plot belum ada → buat baru
        PlotModel newPlot = new PlotModel();
        newPlot.setPLOT(String.valueOf(sample.getPLOT()));
        newPlot.getSAMPLE().add(sample);
        dataPlot.add(newPlot);

        return true;
    }
}
