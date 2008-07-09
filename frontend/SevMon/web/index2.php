<html>
  <head>
    <?php require_once('thermo.class.php'); ?>

    <title>Severity Monitor</title>
    <link rel="stylesheet" type="text/css" href="./resources/css/ext-all.css" />
    <link rel="stylesheet" type="text/css" href="./css/graph.css" />
    <!-- GC -->
    <!-- LIBS -->
    <script type="text/javascript" src="./adapter/ext/ext-base.js"></script>
    <script type="text/javascript" src="./js/prototype.js"></script>
    
    <!-- ENDLIBS -->
    <script type="text/javascript" src="./ext-all.js"></script>
    <!--<script language="javascript" src="./grid/PropsGrid.js"></script>-->
    <style type="text/css">
      html, body {
        font:normal 12px verdana;
        margin:0;
        padding:0;
        border:0 none;
        overflow:hidden;
        height:100%;
      }
      p {
	    margin:5px;
      }
      .settings {
        background-image:url(./shared/icons/fam/folder_wrench.png);
      }
      .nav {
        background-image:url(./shared/icons/fam/folder_go.png);
      }
    </style>
	<script type="text/javascript">
      Ext.onReady(
        function() {
          Ext.state.Manager.setProvider(new Ext.state.CookieProvider());

          var myData = [
            <?php $thermo = new thermo(); echo $thermo->getActivitiesByScore();  ?>
          ];
          var store = new Ext.data.SimpleStore({
            fields: [{name: 'si'},
              {name: 'score', type: 'float'},
              {name: 'lastChange', type: 'date', dateFormat: 'h:i'}]});
          store.loadData(myData);          // Data Source of SI Priorities to display on the right panel.
          var siAirmax = new Ext.grid.GridPanel({
            store: store,
            columns: [
              {id:'si',header: "SI #", width: 160, sortable: true, dataIndex: 'si'},
              {header: "Score", width: 75, sortable: true, dataIndex: 'score'},
              {header: "Time", width: 85, sortable: true, renderer: Ext.util.Format.dateRenderer('h:i'), dataIndex: 'lastChange'}
            ],
            stripeRows: true,
            autoExpandColumn: 'si',
            height:350,
            width:600,
            title:'Airmax'
          });
          var siGeneral = new Ext.grid.GridPanel({
            store: store,
            columns: [
              {id:'si',header: "SI #", width: 160, sortable: true, dataIndex: 'si'},
              {header: "Score", width: 75, sortable: true, dataIndex: 'score'},
              {header: "Time", width: 85, sortable: true, renderer: Ext.util.Format.dateRenderer('h:i'), dataIndex: 'lastChange'}
            ],
            stripeRows: true,
            autoExpandColumn: 'si',
            height:350,
            width:600,
            title:'General'
          });
          var northPanel = new Ext.BoxComponent({
            region:'north',
            el: 'north',
            height:32
          });
          var southPanel = {
            region:'south',
            contentEl: 'south',
            split:true,
            height: 100,
            minSize: 100,
            maxSize: 200,
            collapsible: true,
            title:'La Barra de Abajo',
            margins:'0 0 0 0'}
          var eastPanel = {
            region:'east',
            title: 'Service Incidents',
            //collapsible: true,
            split:true,
            width: 225,
            minSize: 175,
            maxSize: 400,
            layout:'fit',
            margins:'0 5 0 0',
            items: new Ext.TabPanel
            ({
              border:false,
              activeTab:1,
              tabPosition:'bottom',
              items:[
                siGeneral,
                siAirmax
              ]
            })};
          var westPanel = {
            region:'west',
            id:'west-panel',
            title:'Thermometer',
            split:true,
            width: 200,
            minSize: 175,
            maxSize: 400,
            //collapsible: true,
            margins:'0 0 0 5',
            //layout:'accordion',
            layoutConfig:{animate:true},
            items:
            [{
              contentEl: 'termometro',
              border:false,
              iconCls:'airmax'
            }]
          };
          var centerPanel = new Ext.TabPanel
          ({
            region:'center',
            deferredRender:false,
            activeTab:0,
            items:
            [{contentEl:'graphs',
              title: 'Graphs',
              //closable:true,
              autoScroll:true}]
          });
          var viewport = new Ext.Viewport
          ({
            layout:'border',
            items:
            [northPanel,southPanel,eastPanel,westPanel,centerPanel]
          });          
        }
      );
	</script>
  </head>
  <body>
    
    <div id="termometro">
      <div id="graph">
      </div>      
      <script type="text/javascript">
        new Ajax.PeriodicalUpdater('graph', './ajaxUpdate.php', {
          method: 'get', frequency: 2, decay: 1
        });
      </script>
    </div>
    <div id="north">
      <p>
        <center><h1>The Help Desk Severity Monitor</h1></center>
      </p>
    </div>
    <div id="graphs">      
      <div class="demo" id="canvasline" width="800px" height="400px"></div>
      <script type="text/javascript" src="mochikit/MochiKit.js"></script>
      <script type="text/javascript" src="plotkit/excanvas.js"></script>
      <script type="text/javascript" src="plotkit/PlotKit_Packed.js"></script>

<script type="text/javascript">
        //<![CDATA[
        function criticality() {
          var hasCanvas = CanvasRenderer.isSupported();
          <?php $thermo->getCriticalityHigh(); ?>;
          if (hasCanvas) {
            var line = new EasyPlot("line", "", $('canvasline'), [data1, data2, data3]);
          }
        }
        addLoadEvent(criticality);
        //]]>
</script>            
    </div>
    <div id="props-panel" style="width:200px;height:200px;overflow:hidden;">
      <!-- Back Panel ... No usar... -->
    </div>
    <div id="south">
      <p><?php echo "Panel 6"; ?></p>
    </div>
  </body>
</html>
