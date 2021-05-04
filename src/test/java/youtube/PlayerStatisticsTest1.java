package youtube;

import br.com.cursojavanow.dynamicreports.junit.exemplos.youtube.Player;
import br.com.cursojavanow.dynamicreports.junit.exemplos.youtube.PlayerStatistics;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
//https://www.youtube.com/channel/UCwEYX5WFfkw1ivWauyZLkJA/videos
public class PlayerStatisticsTest1 {

    private Player player1;
    private PlayerStatistics statistics;

    @Before
    public void setup() {
        System.out.println("Executando agora o teste >>>");
        player1 = new Player("Patrick", 27);
        statistics = new PlayerStatistics(player1, 3, 3);
    }

    @Test
    public void playerNameEqual() {
        System.out.println("TESTE 1");
        Player player2 = new Player("Patrick", 25);
        assertEquals(player1, player2);
    }

    @Test
    public void playerNamesNotEqual() {
        System.out.println("TESTE 2");
        Player player2 = new Player("Kalvin", 25);
        assertNotEquals(player1, player2);
    }

    @Test
    public void youngerPlayerSame() {
        System.out.println("TESTE 3");
        Player player2 = new Player("Patrick", 25);
        assertSame(player2, PlayerStatistics.getYoungerPlayer(player1, player2));
    }

    @Test
    public void underThirtyTrue() {
        System.out.println("TESTE 4");
        assertTrue(statistics.underThirty());
    }

    @Test
    public void underThirtyFalse() {
        System.out.println("TESTE 5");
        Player player3 = new Player("Patrick", 37);
        PlayerStatistics statistics1 = new PlayerStatistics(player3, 3, 3);
        assertFalse(statistics1.underThirty());
    }

    @Test
    public void csvReportNull() {
        System.out.println("TESTE 6");
        Player player3 = new Player("Patrick", 27);
        PlayerStatistics statistics1 = new PlayerStatistics(player3, 0, 0);
        assertNull(statistics1.createCsvRecord());
    }

    @Test
    public void csvReportNotNull() {
        System.out.println("TESTE 7");
        assertNotNull(statistics.createCsvRecord());
    }

    @Test
    public void getCsvStatsRecord() {
        System.out.println("TESTE 8");
        Player player3 = new Player("Patrick", 27);
        PlayerStatistics statistics1 = new PlayerStatistics(player3, 4, 8);
        Double[] expectedArray = {2d, 0.5};
        assertArrayEquals(expectedArray, statistics1.createCsvRecord());
    }
}
