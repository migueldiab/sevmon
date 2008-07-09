<?php
/**
 * PHP Template.
 *           

 */
 require_once('mysql.class.php');

 class thermo {
    
    // we need to connect with these four pieces of info
    // we will return a database handle, or false
    function thermo() {

    }

    function getActivitiesByScore() {
      $db=new mysql();
      $db->connect();
      $result = $db->query("SELECT activity_id, score, last_updated, activity_name FROM activities;");
      while ($row = mysql_fetch_array($result)) {
        echo "['".$row["activity_id"]."',".$row["score"].",'".$row["last_updated"]."','".addslashes($row["activity_name"])."'],";
      }
    }
    function getThermoemeterBar() {
      $db=new mysql();
      $db->connect();
      $result = $db->query("SELECT MAX(score) FROM activities;");
      $row = mysql_fetch_array($result);
      $max = $row["MAX(score)"];

      $result = $db->query("SELECT MIN(score) FROM activities;");
      $row = mysql_fetch_array($result);
      $min = $row["MIN(score)"];

      $result = $db->query("SELECT COUNT(*) FROM activities WHERE score < '34';");
      $row = mysql_fetch_array($result);
      $low = $row["COUNT(*)"];
      $result = $db->query("SELECT COUNT(*) FROM activities WHERE score > '33' AND score < '67';");
      $row = mysql_fetch_array($result);
      $med = $row["COUNT(*)"];
      $result = $db->query("SELECT COUNT(*) FROM activities WHERE score > '66';");
      $row = mysql_fetch_array($result);
      $high = $row["COUNT(*)"];

      $result = $db->query("INSERT INTO crit_history (score1,score2,score3) VALUES ('".$low."', '".$med."', '".$high."');");
      
      $result = $db->query("SELECT COUNT(DISTINCT score) FROM activities;");
      $row = mysql_fetch_array($result);
      $steps = $row["COUNT(DISTINCT score)"];
      $result = $db->query("SELECT COUNT(*) FROM activities;");
      $row = mysql_fetch_array($result);
      $cant = $row["COUNT(*)"];
      
      //echo "El max es : ".$max;
      $r=255;
      $g=25;
      $b=25;
      if (!$steps) $steps=11;

      $result = $db->query("SELECT COUNT(*), score FROM activities GROUP BY score DESC;");
      while ($row = mysql_fetch_array($result)) {
        $rh = str_pad(dechex($r), 2, "0", STR_PAD_LEFT);
        $gh = str_pad(dechex($g), 2, "0", STR_PAD_LEFT);
        $bh = str_pad(dechex($b), 2, "0", STR_PAD_LEFT);
        $color = $rh.$gh.$bh;
        $pctg=round((100/$cant)*$row['COUNT(*)']);
        echo "<tr style=\"background-color:#".$color.";\" height=\"".$pctg."%\">";
        echo "<td align='center'>
              </td><center><b>".$row['COUNT(*)']."</b></center></tr>
          ";
        $r=round($r-(180/$steps));
        $g=round($g+(100/$steps));
        $b=round($b+(225/$steps));
      }
    }
    function getCriticality() {
      $db=new mysql();
      $db->connect();
      $result = $db->query("SELECT * FROM `crit_history` order by updated_at desc LIMIT 20;");
      $row = mysql_fetch_array($result);
      $i=0;
       $low.= "var data1 = [[";
       $med.= "var data2 = [[";
       $high.= "var data3 = [[";
     while ($row = mysql_fetch_array($result)) {
       $low.= $i.",".$row["score1"]."],[";
       $med.= $i.",".$row["score1"]."],[";
       $high.= $i.",".$row["score1"]."],[";
       $i++;
     }
     $low.= "20,250]];
      ";
     $med.= "20,250]];
       ";
     $high.= "20,250]];
        ";
     echo $low;
     echo $med;
     echo $high;

    }
 }
?>