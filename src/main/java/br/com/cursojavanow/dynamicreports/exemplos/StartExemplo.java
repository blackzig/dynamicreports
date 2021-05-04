/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cursojavanow.dynamicreports.exemplos;

/**
 *
 * @author michel adriano medeiros
 */
public class StartExemplo {

    public static void callReport(ReportType reportType) {
        switch (reportType) {
            case SimpleAdhocReport:
                new SimpleAdhocReport();
                break;
            case AdhocCustomizerReport:
                new AdhocCustomizerReport();
                break;
            default:
        }

    }

}
