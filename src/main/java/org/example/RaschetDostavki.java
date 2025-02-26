package org.example;

import java.util.HashMap;

public class RaschetDostavki {

    private static final HashMap<String, Double> hashZagruzhennost = new HashMap<>();
    static {
        hashZagruzhennost.put("Ochen_visokaya", 1.6);
        hashZagruzhennost.put("Visokaya", 1.4);
        hashZagruzhennost.put("Povishennaya", 1.2);
        hashZagruzhennost.put("Obichnaya", 1.0);
    }

    public int StoimostDostavki(int rasstoyanie, boolean gabarity, boolean hrupkost, String zagruzhennost) {

        if (rasstoyanie < 0) {
            throw new IllegalArgumentException("Расстояние не может быть меньше 0");
        }

        if (hrupkost && rasstoyanie > 30) {
            throw new IllegalArgumentException("Нельзя возить хрупкий товар на расстояние свыше 30 км. Расчет доставки невозможен");
        }

        int stoimost = 0;
        int ItogGabarity = gabarity ? 200 : 100;

        if (rasstoyanie < 2) {
            stoimost += 50;
        } else if (rasstoyanie < 10) {
            stoimost += 100;
        } else if (rasstoyanie < 30) {
            stoimost += 200;
        } else {
            stoimost += 300;
        }

        if (hrupkost) {
            stoimost += 300;
        }

        stoimost = (int) Math.round ((stoimost + ItogGabarity) * hashZagruzhennost.getOrDefault(zagruzhennost, 1.0));

        return Math.max(400, stoimost);
    }
}