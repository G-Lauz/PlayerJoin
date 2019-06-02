package fr.freebuild.playerjoin.utils;

import lombok.Getter;

public enum FormatParam {
  PLAYER("{playername}"),
  COUNTER("{counter}");

  @Getter
  private String value;

  FormatParam(String param){
    this.value = param;
  }
}