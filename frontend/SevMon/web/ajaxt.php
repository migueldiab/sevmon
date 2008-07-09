<html>
  <head>
    <script type="text/javascript" src="./js/prototype.js"></script>
  </head>
  <body>
    <form>
      First Name:<input type="text" id="txt1" onkeyup="showHint(this.value)">
    </form>
    <p>Suggestions: <span id="txtHint"></span></p>
    <script type="text/javascript">
      new Ajax.PeriodicalUpdater('txtHint', 'test.txt', {
        method: 'get', frequency: 3, decay: 1
      });
    </script>
  </body>
</html>

