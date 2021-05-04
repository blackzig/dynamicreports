package br.com.cursojavanow.dynamicreports.dinamico;

import java.util.List;
import net.sf.dynamicreports.adhoc.AdhocManager;
import net.sf.dynamicreports.adhoc.configuration.AdhocColumn;
import net.sf.dynamicreports.adhoc.configuration.AdhocConfiguration;
import net.sf.dynamicreports.adhoc.configuration.AdhocReport;
import net.sf.dynamicreports.adhoc.report.DefaultAdhocReportCustomizer;
import net.sf.dynamicreports.adhoc.transformation.AdhocToXmlTransform;
import net.sf.dynamicreports.adhoc.transformation.XmlToAdhocTransform;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import static net.sf.dynamicreports.report.builder.DynamicReports.type;
import net.sf.dynamicreports.report.builder.ReportBuilder;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.definition.datatype.DRIDataType;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;

/**
 *
 * @author michel adriano medeiros
 */
public class AdhocCustomizerReport {

    public AdhocCustomizerReport(List<String> colunas, List<String> valores) {
        AdhocManager adhocManager = AdhocManager.getInstance(new AdhocToXmlTransform(), new XmlToAdhocTransform());
        AdhocConfiguration configuration = new AdhocConfiguration();
        AdhocReport report = new AdhocReport();
        configuration.setReport(report);
        report = criarColunas(colunas, report);
        try {
            JasperReportBuilder reportBuilder = adhocManager.createReport(configuration.getReport(), new ReportCustomizer());
           // reportBuilder.setDataSource(createDataSource(report, valores));
            reportBuilder.show();
        } catch (DRException e) {
            System.out.println("ERRO AdhocCustomizerReport " + e.getMessage());
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

//    private JRDataSource createDataSource(AdhocReport report, List<String> valores) {
//        int numeroDeColunas = report.getColumns().size();
//        DRDataSource dataSource = nomesDasColunas(numeroDeColunas, report);
//        dataSource = dadosParaAsColunas(dataSource, numeroDeColunas, valores);
//        return dataSource;
//    }

    private class ReportCustomizer extends DefaultAdhocReportCustomizer {

        /**
         * If you want to add some fixed content to a report that is not needed
         * to store in the xml file. For example you can add default page
         * header, footer, default fonts,...
         */
        @Override
        public void customize(ReportBuilder<?> report, AdhocReport adhocReport) throws DRException {
            super.customize(report, adhocReport);
            // default report values
            report.setTemplate(Templates.reportTemplate);
            report.title(Templates.createTitleComponent("Título do Relatório"));
            // a fixed page footer that user cannot change, this customization is not stored in the xml file
            report.pageFooter(Templates.footerComponent);
        }

//        /**
//         * This method returns a field type from a given field name.
//         */
//        @Override
//        protected DRIDataType<?, ?> getFieldType(String name) {
//            if (name.equals("item")) {
//                return type.stringType();
//            }
//            if (name.equals("orderdate")) {
//                return type.dateType();
//            }
//            if (name.equals("quantity")) {
//                return type.integerType();
//            }
//            if (name.equals("unitprice")) {
//                return type.bigDecimalType();
//            }
//            return super.getFieldType(name);
//        }
//
//        /**
//         * If a user doesn’t specify a column title, getColumnTitle is evaluated
//         * and the return value is used as a column title.
//         */
//        @Override
//        protected String getFieldLabel(String name) {
//            if (name.equals("item")) {
//                return "Item";
//            }
//            if (name.equals("orderdate")) {
//                return "Order date";
//            }
//            if (name.equals("quantity")) {
//                return "Quantity";
//            }
//            if (name.equals("unitprice")) {
//                return "Unit price";
//            }
//            return name;
//        }
    }

}
