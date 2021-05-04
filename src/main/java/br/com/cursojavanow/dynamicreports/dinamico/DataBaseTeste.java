package br.com.cursojavanow.dynamicreports.dinamico;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author michel adriano medeiros
 */
public class DataBaseTeste {

    public static void callSimpleAdhocReport() {
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
