<script type="text/javascript">
      //<![CDATA[
      function clear() {
          alert('clear');
          document.getElementById('canvasline').innerHTML="";
      }
      function criticality() {
        var hasCanvas = CanvasRenderer.isSupported();
        <?php $thermo = new thermo(); $thermo->getCriticality(); ?>;
        if (hasCanvas) {
          var line = new EasyPlot("line", "", $('canvasline'), [data1, data2, data3]);
        }
      }
      addLoadEvent(criticality);
      //]]>
</script>