package br.com.cursojavanow.dynamicreports.exemplos;

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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;
import java.util.stream.IntStream;

/**
 * <p>
 * SimpleAdhocReport class.</p>
 *
 * @author Ricardo Mariaca
 *
 */
public class SimpleAdhocReport {

    /**
     * <p>
     * Constructor for SimpleAdhocReport.</p>
     */
    public SimpleAdhocReport() {
        build();
    }

    private void build() {

        AdhocManager adhocManager = AdhocManager.getInstance(new AdhocToXmlTransform(), new XmlToAdhocTransform());

        AdhocConfiguration configuration = new AdhocConfiguration();
        AdhocReport report = new AdhocReport();
        configuration.setReport(report);

        AdhocColumn column = new AdhocColumn();
        column.setName("item");
        report.addColumn(column);

        column = new AdhocColumn();
        column.setName("quantity");
        report.addColumn(column);

        try {
            // The following code stores the configuration to an xml file
            //  adhocManager.saveConfiguration(configuration, new FileOutputStream("c:/temp/configuration.xml"));
            @SuppressWarnings("unused")
            // The following code loads a configuration from an xml file
            // AdhocConfiguration loadedConfiguration = adhocManager.loadConfiguration(new FileInputStream("c:/temp/configuration.xml"));

            JasperReportBuilder reportBuilder = adhocManager.createReport(configuration.getReport());
            reportBuilder.setDataSource(createDataSource());
            reportBuilder.show();
        } catch (DRException e) {
            System.out.println("ERRO " + e.getMessage());
        }
    }

    private JRDataSource createDataSource() {
        //  DRDataSource dataSource = new DRDataSource(report.getColumns().get(0).getName(), report.getColumns().get(1).getName());
        DRDataSource dataSource = new DRDataSource("item", "orderdate", "quantity", "unitprice");
        IntStream.range(0, 20).forEach(i -> dataSource.add("Book # " + i, Date.from(Instant.now()), (int) (Math.random() * 10) + 1, BigDecimal.valueOf(Math.random() * 100 + 1)));
        return dataSource;
    }
}
