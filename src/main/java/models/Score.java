package models;

import lombok.Data;

/**
 * @author arnab.ray
 * @created on 23/08/22
 */
@Data
public class Score {
    private String homeTeam;
    private String visitingTeam;
    private int homeScore;
    private int visitingScore;
    private Long updateTimestamp;
}
