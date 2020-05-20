package org.dcp.plugin.example

import com.unifi.executor.plugin.api.{Plugin, UserDefinedDataType}
import com.unifi.executor.plugin.core.types.{PrimitiveDataType, StringType}

class ExamplePlugin extends Plugin {
  def name: String        = "My Example Plugin"
  def uid: String         = "pluginuid123"
  def description: String = "Example Plugin with udts"
  def namespace: String   = "org"
  def version: String     = "1.0"

  override def getDataTypes(): List[UserDefinedDataType] = List(OrderStatus)

  override def getFunctions() = List.empty

  // My data type that does a lookup on a data dictionary to
  // validate
  case object OrderStatus extends UserDefinedDataType {

    // Initialize my data dictionary
    val orderStatusList = initializeDataDictionary("/orderstatus.csv")

    override def matchValue(value: String): Boolean = {
      orderStatusList.contains(value.toLowerCase)
    }

    private def initializeDataDictionary(path: String): Set[String] = {
      val src = scala.io.Source.fromInputStream(getClass.getResourceAsStream(path))
      val items = src.getLines().drop(1).map(_.toLowerCase).toSet
      return items
    }

    // Name of the data type
    override def name: String = "OrderStatus"

    // description
    override def description: String = "Data type representing order status values."

    // Optional UDT parent to influence type hierarchy
    override def parent: Option[UserDefinedDataType] = None

    override def hints: List[PrimitiveDataType] = List(StringType)

    // Underlying unifi storage type for this UDT
    override def unifiType: PrimitiveDataType = StringType

    // UID
    override def uid: String = "datatypeuid"
  }
}
