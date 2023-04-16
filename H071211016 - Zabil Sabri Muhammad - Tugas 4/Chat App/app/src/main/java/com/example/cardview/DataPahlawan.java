package com.example.cardview;

import java.util.ArrayList;

public class DataPahlawan {

    public static String[][] data = new String[][]{
            {"Amel", "Halo Sayang", "10:10 PM", "https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEg_jRYnAxSLfnWiVC07FmMUcEYIR_9NWRsM5Z0dc4GHobw_6Dz71H5vCwzicxDzb1eQAVPavc9eNG7fB1IZgvfJLj7spcAanfhDsSfbGqg6zMrJpmQfUescjAyw_f1PCKBH-gQmqI4Igt7n6coShldhilgTYV58Qx5UPc-0dvMmulyToTchIgrQ6Jhr4g/s1104/cewek%20cantik%20berhijab%20(4).jpg", "08114613767", "Lagi Galau", "17 Maret 2023"},
            {"Putri", "Om, Mau Iphone 13 :(", "12:10 PM", "https://blog.pengajartekno.com/wp-content/uploads/2022/11/foto-cewek2-cantik-berhijab-20.jpg", "0890903734", "Lagi Happy", "16 Maret 2023"},
            {"Alysa", "Hai Ganteng!", "11:00 AM", "https://blog.pengajartekno.com/wp-content/uploads/2022/11/foto-cewek2-cantik-berhijab-19.jpg", "93475347503", "Busy", "20 April 2023"},
            {"Intan", "Kamu Lagi Dimana?", "09:00 PM", "https://blog.pengajartekno.com/wp-content/uploads/2022/11/foto-cewek2-cantik-berhijab-18.jpg", "09489374057", "Lagi Wedding", "11 Maret 2023"},
            {"Fitri", "Sharelock Bang!", "08:00 PM", "https://blog.pengajartekno.com/wp-content/uploads/2022/11/foto-cewek2-cantik-berhijab-17.jpg", "0934024572045", "Busy Like Hell", "29 Maret 2023"},
            {"Nabila", "Hai Jalan Yuk!", "06:00 PM", "https://blog.pengajartekno.com/wp-content/uploads/2022/11/foto-cewek2-cantik-berhijab-16.jpg", "09809809808", "Lagi Berpuasa", "12 Januari 2023"},
            {"Nadila", "Bukber Yuk!", "07:00 PM", "https://blog.pengajartekno.com/wp-content/uploads/2022/11/foto-cewek2-cantik-berhijab-15.jpg", "08114613767", "Lagi Sehat", "17 Februari 2023"},
            {"Andini", "Makan Yuk!", "12:00 PM", "https://blog.pengajartekno.com/wp-content/uploads/2022/11/foto-cewek2-cantik-berhijab-14.jpg", "08114613767", "Lagi Hepi", "17 Agustus 2023"},
            {"Azkia", "Udah Bobo Blum?", "10:00 PM", "https://blog.pengajartekno.com/wp-content/uploads/2022/11/foto-cewek2-cantik-berhijab-13.jpg", "08114613767", "Lagi Kerja Tugas", "17 Desember 2023"},
            {"Athifa", "Selamat Berbuka Sayang!", "11:00 AM", "https://blog.pengajartekno.com/wp-content/uploads/2022/11/foto-cewek2-cantik-berhijab-12.jpg", "08114613767", "Lagi Menunggu Bus", "17 Mei 2020"},
    };

    public static ArrayList<ModelPahlawan>
    ambilDataPahlawan() {
        ArrayList<ModelPahlawan> dataPahlawan = new ArrayList<>();
        for (String[] varData : data) {
            ModelPahlawan model = new ModelPahlawan();
            model.setNama(varData[0]);
            model.setTentang(varData[1]);
            model.setWaktu(varData[2]);
            model.setFoto(varData[3]);
            model.setNomor(varData[4]);
            model.setStatus(varData[5]);
            model.setTgl_status(varData[6]);
            dataPahlawan.add(model);
        }
        return dataPahlawan;
    }
}
