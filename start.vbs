Set oShell = WScript.CreateObject("WScript.shell")
Dim strArgs
strArgs = "grails prod run-app"
oShell.Run strArgs, 0, false