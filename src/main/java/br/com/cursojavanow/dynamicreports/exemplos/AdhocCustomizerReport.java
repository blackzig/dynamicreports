package br.com.cursojavanow.dynamicreports.exemplos;

import net.sf.dynamicreports.adhoc.AdhocManager;
import net.sf.dynamicreports.adhoc.configuration.AdhocCalculation;
import net.sf.dynamicreports.adhoc.configuration.AdhocColumn;
import net.sf.dynamicreports.adhoc.configuration.AdhocConfiguration;
import net.sf.dynamicreports.adhoc.configuration.AdhocGroup;
import net.sf.dynamicreports.adhoc.configuration.AdhocReport;
import net.sf.dynamicreports.adhoc.configuration.AdhocSort;
import net.sf.dynamicreports.adhoc.configuration.AdhocSubtotal;
import net.sf.dynamicreports.adhoc.report.DefaultAdhocReportCustomizer;
import net.sf.dynamicreports.adhoc.transformation.AdhocToXmlTransform;
import net.sf.dynamicreports.adhoc.transformation.XmlToAdhocTransform;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.ReportBuilder;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.definition.datatype.DRIDataType;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;
import java.util.stream.IntStream;

import static net.sf.dynamicreports.report.builder.DynamicReports.type;

/**
 * <p>
 * AdhocCustomizerReport class.</p>
 *
 * @author Ricardo Mariaca
 *
 */
public class AdhocCustomizerReport {

    /**
     * <p>
     * Constructor for AdhocCustomizerReport.</p>
     */
    public AdhocCustomizerReport() {
        build();
    }

    /**
     * <p>
     * main.</p>
     *
     * @param args an array of {@link java.lang.String} objects.
     */
    public static void main(String[] args) {
        new AdhocCustomizerReport();
    }

    private void build() {
        AdhocManager adhocManager = AdhocManager.getInstance(new AdhocToXmlTransform(), new XmlToAdhocTransform());
        AdhocConfiguration configuration = new AdhocConfiguration();
        AdhocReport report = new AdhocReport();
        configuration.setReport(report);

        // columns
        AdhocColumn column = new AdhocColumn();
        column.setName("quantity");
        report.addColumn(column);
        column = new AdhocColumn();
        column.setName("unitprice");
        report.addColumn(column);
        // groups (cria subgrupos no relatório, nesse caso é o PDA e BOOK)
        AdhocGroup group = new AdhocGroup();
        group.setName("item");
        report.addGroup(group);
        // subtotal
        AdhocSubtotal subtotal = new AdhocSubtotal();
        subtotal.setName("quantity");
        subtotal.setCalculation(AdhocCalculation.COUNT);//conta os itens da coluna quantity
        report.addSubtotal(subtotal);
        subtotal = new AdhocSubtotal();
        subtotal.setCalculation(AdhocCalculation.SUM);
        subtotal.setName("unitprice");
        report.addSubtotal(subtotal);
        // sorts (ordenando os subgrupos, nesse caso é o PDA BOOK)
        AdhocSort sort = new AdhocSort();
        sort.setName("item");
        report.addSort(sort);

        try {
            JasperReportBuilder reportBuilder = adhocManager.createReport(configuration.getReport(), new ReportCustomizer());
            reportBuilder.setDataSource(createDataSource());
            reportBuilder.show();
        } catch (DRException e) {
            e.printStackTrace();
        }
    }

    private JRDataSource createDataSource() {
        DRDataSource dataSource = new DRDataSource("item", "orderdate", "quantity", "unitprice");
        IntStream.range(0, 15).forEachOrdered(i -> dataSource.add("PDA ", Date.from(Instant.now()), (int) (Math.random() * 10) + 1, BigDecimal.valueOf(Math.random() * 100 + 1)));
        IntStream.range(0, 20).forEachOrdered(i -> dataSource.add("BOOK", Date.from(Instant.now()), (int) (Math.random() * 10) + 1, BigDecimal.valueOf(Math.random() * 100 + 1)));
        return dataSource;
    }

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
            report.title(Templates.createTitleComponent("AdhocCustomizer"));
            // a fixed page footer that user cannot change, this customization is not stored in the xml file
            report.pageFooter(Templates.footerComponent);
        }

        /**
         * This method returns a field type from a given field name.
         * Define o tipo da variável dos dados da coluna
         */
        @Override
        protected DRIDataType<?, ?> getFieldType(String name) {
            if (name.equals("item")) {
                return type.stringType();
            }
            if (name.equals("orderdate")) {
                return type.dateType();
            }
            if (name.equals("quantity")) {
                return type.integerType();
            }
            if (name.equals("unitprice")) {
                return type.bigDecimalType();
            }
            return super.getFieldType(name);
        }

        /**
         * If a user doesn’t specify a column title, getColumnTitle is evaluated
         * and the return value is used as a column title.
         * Arruma o nome das colunas, mas se colocar o nome já arrumado ao
         * adicionar a coluna, esse método não é necessário.
         * 
         */
        @Override
        protected String getFieldLabel(String name) {
            if (name.equals("item")) {
                return "Item";
            }
            if (name.equals("orderdate")) {
                return "Order date";
            }
            if (name.equals("quantity")) {
                return "Quantity";
            }
            if (name.equals("unitprice")) {
                return "Unit price";
            }
            return name;
        }

    }
}
