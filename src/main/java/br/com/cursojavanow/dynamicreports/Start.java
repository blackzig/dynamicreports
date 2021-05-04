package br.com.cursojavanow.dynamicreports;

import br.com.cursojavanow.dynamicreports.dinamico.DataBaseTeste;
import br.com.cursojavanow.dynamicreports.exemplos.ReportType;
import br.com.cursojavanow.dynamicreports.exemplos.StartExemplo;

/**
 *
 * @author michel adriano medeiros
 */
public class Start {

    public static void main(String[] args) {
        //Exemplos originais
        StartExemplo.callReport(ReportType.AdhocCustomizerReport);
        //Meus dinâmicos
       // DataBaseTeste.callSimpleAdhocReport();
    }

}
