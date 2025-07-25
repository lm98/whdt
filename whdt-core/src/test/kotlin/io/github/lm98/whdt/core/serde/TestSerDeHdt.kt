package io.github.lm98.whdt.core.serde

import io.github.lm98.whdt.core.hdt.HumanDigitalTwin
import io.github.lm98.whdt.core.hdt.interfaces.digital.MqttDigitalInterface
import io.github.lm98.whdt.core.hdt.interfaces.physical.MqttPhysicalInterface
import io.github.lm98.whdt.core.hdt.model.Model
import io.github.lm98.whdt.core.hdt.model.property.BloodPressure
import io.github.lm98.whdt.core.hdt.model.property.GivenName
import io.github.lm98.whdt.core.hdt.model.property.HeartRate
import io.github.lm98.whdt.core.hdt.model.property.Surname
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class TestSerDeHdt: FunSpec({
  test("Test SerDe HumanDigitalTwin") {
      val properties = listOf(
          GivenName("John"),
          Surname("Doe"),
          BloodPressure(
              systolic = 120,
              diastolic = 80,
          ),
          HeartRate(72)
      )
      val model = Model(properties)
      val pI = MqttPhysicalInterface(
          properties = properties,
          clientId = "hd1",
      )
      val dI = MqttDigitalInterface(
          properties = properties,
          clientId = "hd1",
      )
      val hdt = HumanDigitalTwin(
          id = "hd1",
          models = listOf(model),
          physicalInterfaces = listOf(pI),
          digitalInterfaces = listOf(dI),
      )

      val serialized = Stub.hdtJsonSerDe().serialize(hdt)
      //println(serialized)
      val deserialized = Stub.hdtJsonSerDe().deserialize(serialized)

      deserialized shouldBe hdt
  }
})