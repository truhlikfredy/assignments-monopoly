@echo off

rem Switching to unicode with "chcp 65001" doesn't help

echo This application assumes UTF-8 console or the text graphics will be broken
java -jar MonopolyGame.jar
