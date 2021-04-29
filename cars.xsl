<?xml version="1.0" encoding="UTF-8" standalone="no"?>

<xsl:stylesheet version="1.0"
xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:template match="/">
  <html>
  <body>
  <h2>Soprty Cars</h2>
  <table border="1">
    <tr bgcolor="#9acd32">
      <th>Year</th>
      <th>Make</th>
      <th>Model</th>
      <th>Price</th>
    </tr>
 <xsl:for-each select="SportyCars/Row">
    <tr>
      <td><xsl:value-of select="YEAR"/></td>
      <td><xsl:value-of select="MAKE"/></td>
      <td><xsl:value-of select="MODEL"/></td>
      <td><xsl:value-of select="PRICE"/></td>
    </tr>
    </xsl:for-each>
  </table>
  </body>
  </html>
</xsl:template>

</xsl:stylesheet>
