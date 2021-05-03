/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cursojavanow.dynamicreports;

import br.com.cursojavanow.dynamicreports.dinamico.SimpleAdhocReport;
import br.com.cursojavanow.dynamicreports.exemplos.StartExemplo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author zigui
 */
public class Start {

    public static void main(String[] args) {
        //    StartExemplo.simpleAdhocReport();
        List<String> colunas = new ArrayList<>();
        colunas.add("ID");
        colunas.add("Nome");
        colunas.add("Telefone");

        List<String> valores = new ArrayList<>();
        valores.add("1");
        valores.add("Michel Adriano Medeiros");
        valores.add("(19)991923566");
        valores.add("2");
        valores.add("Angela Ferraz");
        valores.add("(19)991923567");

        new SimpleAdhocReport(colunas, valores);
    }

}
