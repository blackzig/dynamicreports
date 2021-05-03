package br.com.cursojavanow.dynamicreports.dinamico;

import java.util.List;
import net.sf.dynamicreports.adhoc.AdhocManager;
import net.sf.dynamicreports.adhoc.configuration.AdhocColumn;
import net.sf.dynamicreports.adhoc.configuration.AdhocConfiguration;
import net.sf.dynamicreports.adhoc.configuration.AdhocReport;
import net.sf.dynamicreports.adhoc.transformation.AdhocToXmlTransform;
import net.sf.dynamicreports.adhoc.transformation.XmlToAdhocTransform;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;

/**
 *
 * @author michel adriano medeiros
 */
public class SimpleAdhocReport {

    public SimpleAdhocReport(List<String> colunas, List<String> valores) {
        AdhocManager adhocManager = AdhocManager.getInstance(new AdhocToXmlTransform(), new XmlToAdhocTransform());
        AdhocConfiguration configuration = new AdhocConfiguration();
        AdhocReport report = new AdhocReport();
        configuration.setReport(report);
        report = criarColunas(colunas, report);
        try {
            JasperReportBuilder reportBuilder = adhocManager.createReport(configuration.getReport());
            reportBuilder.setDataSource(createDataSource(report, valores));
            reportBuilder.show();
        } catch (DRException e) {
            System.out.println("ERRO SimpleAdhocReport " + e.getMessage());
        }
    }

    private AdhocReport criarColunas(List<String> colunas, AdhocReport report) {
        for (String coluna : colunas) {
            AdhocColumn column = new AdhocColumn();
            column.setName(coluna);
            report.addColumn(column);
        }
        return report;
    }

    private JRDataSource createDataSource(AdhocReport report, List<String> valores) {
        int numeroDeColunas = report.getColumns().size();
        DRDataSource dataSource = nomesDasColunas(numeroDeColunas, report);
        dataSource = dadosParaAsColunas(dataSource, numeroDeColunas, valores);
        return dataSource;
    }

    private DRDataSource nomesDasColunas(int numeroDeColunas, AdhocReport report) {
        DRDataSource dataSource = new DRDataSource();
        switch (numeroDeColunas) {
            case 1:
                dataSource = new DRDataSource(report.getColumns().get(0).getName());
                break;
            case 2:
                dataSource = new DRDataSource(report.getColumns().get(0).getName(), report.getColumns().get(1).getName());
                break;
            case 3:
                dataSource = new DRDataSource(report.getColumns().get(0).getName(), report.getColumns().get(1).getName(), report.getColumns().get(2).getName());
                break;
            default:
        }
        return dataSource;
    }

    private DRDataSource dadosParaAsColunas(DRDataSource dataSource, int numeroDeColunas, List<String> valores) {
        switch (numeroDeColunas) {
            case 1:
                for (int i = 0; i < valores.size(); i += numeroDeColunas) {
                    dataSource.add(valores.get(i));
                }
                break;
            case 2:
                for (int i = 0; i < valores.size(); i += numeroDeColunas) {
                    dataSource.add(valores.get(i), valores.get(i+1));
                }
                break;
            case 3:
                for (int i = 0; i < valores.size(); i += numeroDeColunas) {
                    dataSource.add(valores.get(i), valores.get(i+1), valores.get(i+2));
                }
                break;
            default:
        }
        return dataSource;
    }

}
