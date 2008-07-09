<?php
/**
 * PHP Template.
 */

?>
<script type="text/javascript">
        //<![CDATA[
        function criticality() {
          var hasCanvas = CanvasRenderer.isSupported();
        <?php $thermo = new thermo(); $thermo->getCriticality(); ?>;
          if (hasCanvas) {
            var line = new EasyPlot("line", "", $('canvasline'), [data1, data2, data3]);
          }
        }
        criticality();
        //]]>
</script>