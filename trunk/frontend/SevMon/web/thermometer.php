<?php
/**
 * PHP Template.
 */
  require_once('thermo.class.php');
  //$thermo = new thermo();
  //echo $thermo->getActivitiesByScore(); 
?>
        <table height="100%" width="90%" border="0" cellspacing="0" cellpadding="0">
          <?php $thermo = new thermo(); echo $thermo->getThermoemeterBar();  ?>
        </table>
