<?php
  /**
   * PHP Template.
   */
  class mysql {
    var $handle;
    var $server = 'localhost';
    var $user = 'testuser';
    var $pass = 'test123';
    var $database ='sevmon';
    var $result;

    // we need to connect with these four pieces of info
    // we will return a database handle, or false
    function mysql() {

    }
    function connect() {
      $this->handle = mysql_connect($this->server, $this->user, $this->pass);
      if (!$this->handle) {
        return false;
      }
      $status = mysql_select_db($this->database, $this->handle);
      if (!$status) {
        return false;
      }
    }
    // pass in query and handle
    // return result pointer or false
    function query($query) {
      $result = mysql_query($query, $this->handle);
      return $result;
    }
    // take result pointer and return next row
    function retrieve() {
      $row = mysql_fetch_assoc($this->result);
      return $row;
    }
    // close a mysql handle
    function disconnect() {
      mysql_close($this->handle);
    }
  }

?>
