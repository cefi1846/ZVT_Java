# ZVT_Java
Version 2.0.1
Implementierung des ZVT-Protokolls für Zahlungsverkehrsterminals in der Programmiersprache Java auf Basis von TCP/IP!

In diesem Repository implementiere ich das als quasi-Standard geltende ZVT-Protokoll für Kartenterminals auf Basis von TCP/IP. Die Dokumentation zum ZVT-Protokoll ist extrem umfangreich. Jede erdenkliche Funktion einer Zahlung lässt sich implementieren. Die Implementation ist nicht trivial, zeitaufwendig und erfordert großes technisches Wissen sowie Verständnis! Viele Parameter sind in BMP bzw. TLV-Containern untergebracht, diese müssen decodiert werden etc.

Dieses Projekt kann als fertige API bei CeFiSystems bezogen werden und individuell für Ihre Anwendung um Funktionen aus dem ZVT-Protokollkanon erweitert werden.

Es existiert darüber hinaus eine Debug-App für Android, welche das komplette ZVT-Protokoll und die gesamte ZVT-API von CeFiSystems enthält. Diese App soll als Test dienen, um die Verbindung mit dem Terminal zu testen.

Folgende Funktionen stehen im Standard zur Verfügung:
- Anmeldung am Terminal
- Abmeldung am Terminal
- Ausführung einer Zahlung (online/offline mit/ohne PIN-Abfrage)
- Stornierung einer Zahlung
- Darstellung individueller Texte auf dem Terminaldisplay (Je nach Terminaldisplay)
- Auslesen der Magnetspuren (Spur 1, 2, 3)
- Auslesen des Chips der Karte
- Tagesabschluss senden
- Druck bei Kasse (Zahlungs-/Administrationsbelege werden an der Kasse gedruckt, Integration in Bon möglich)
- Druck am Terminal (Zahlungs-/Administrationselege werden am Terminal gedruckt)
- Weitergabe der Stautsinformationen einer Zahlung an die Kasse (Karte stecken, Karte ziehen, Bite warten..., Zahlung erfolgt, Zahlung fehlgeschlagen) etc.

Geplante Funktionen: -> % gibt an, in welchem Stadium sich die jeweilige Funktion befindet. 0% nochgar nicht, 50% geplant, 100% in der Entwicklung.
- Prepaid-Funktionalitäten (Laden, entladen, Telefon aufladen) - 0%
- Darstellung individueller Logos (nur modere Teminals) - 90%
- Kreditkartenreservierung setzen - 50%
- Kreditkartenreservierung aufheben - 50%
- Kundenkartenanbindung - 70%
- Altersprüfung mittels Geldkarte-Chip (Nur Deutschland) - 20%
- Autonomer Betrieb für Tankautomaten/Spielautomaten etc. - 50%



Gerne stehe ich Ihnen bzw. Ihrer Firma als Berater zum Thema ZVT-Schnittstelle/Implementation der ZVT-Schnittstelle in Ihre Anwendung zur Verfügung.

Für weitere Informationen senden Sie mir bitte eine Mail an: c.fischer@cefisystems.de
Oder besuchen Sie unsere Seite: https://www.cefisystems.de/zvt-java-api


!!! ---> Dieses Projekt steht nicht mehr öffentlich zur Verfügung. Dieses Repo dient nur noch der Information. <--- !!!
