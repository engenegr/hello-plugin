package com.clightning.template

import jrpc.clightning.plugins.CLightningPlugin
import jrpc.clightning.annotation.Hook
import jrpc.clightning.annotation.PluginOption
import jrpc.clightning.annotation.RPCMethod
import jrpc.clightning.annotation.Subscription
import jrpc.clightning.plugins.log.PluginLog
import jrpc.service.converters.jsonwrapper.CLightningJsonObject

class Plugin extends CLightningPlugin(){

  @PluginOption(
    name = "hc-proxy",
    description = "This option give information on proxy enabled, by default set on tor proxy",
    defValue = "127.0.0.1:9050",
    typeValue = "string"
  )
  private var proxy: String = "127.0.0.1:9050"

  private var pluginInit = false

  @RPCMethod(name = "annotation_hello", description = "Annotation plugin") def hello(plugin: CLightningPlugin, request: CLightningJsonObject, response: CLightningJsonObject): Unit = {
    log(PluginLog.WARNING, request.toString)
    response.add("type", "random")
  }

  @Subscription(notification = "invoice_creation") def doInvoiceCreation(data: CLightningJsonObject): Unit = {
    log(PluginLog.WARNING, "Notification invoice_creation received inside the plugin lightning rest")
    log(PluginLog.WARNING, "Data received by notification are \n" + data.toString)
  }

  @Hook(hook = "custommsg") def logAllRPCCommand(plugin: CLightningPlugin, request: CLightningJsonObject, response: CLightningJsonObject): Unit = {
    log(PluginLog.WARNING, "custommsg")
    log(PluginLog.WARNING, request.toString)
    response.add("result", "continue")
  }

  @Hook(hook = "htlc_accepted") def htlcAcceptedHook(plugin: CLightningPlugin, request: CLightningJsonObject, response: CLightningJsonObject): Unit = {
    log(PluginLog.WARNING, "htlc_accepted")
    log(PluginLog.WARNING, request.toString)
    response.add("result", "continue")
  }

  @Hook(hook = "peer_connected") def peerConnectedHook(plugin: CLightningPlugin, request: CLightningJsonObject, response: CLightningJsonObject): Unit = {
    log(PluginLog.WARNING, "peer_connected")
    log(PluginLog.WARNING, request.toString)
    response.add("result", "continue")
  }
}

object Plugin extends App {
  var pluginCL = new Plugin
  pluginCL.log(PluginLog.INFO, "starting...")
  pluginCL.start()
  pluginCL.log(PluginLog.INFO, "quit main()")
}
