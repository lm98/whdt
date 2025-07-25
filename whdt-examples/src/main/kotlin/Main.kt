package io.github.lm98

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import io.github.lm98.whdt.wldt.plugin.execution.WldtApp
import io.github.lm98.whdt.core.hdt.HumanDigitalTwin
import io.github.lm98.whdt.core.hdt.interfaces.digital.MqttDigitalInterface
import io.github.lm98.whdt.core.hdt.interfaces.physical.MqttPhysicalInterface
import io.github.lm98.whdt.core.hdt.model.Model
import io.github.lm98.whdt.core.hdt.model.property.BloodPressure
import io.github.lm98.whdt.core.hdt.model.property.GivenName
import io.github.lm98.whdt.core.hdt.model.property.HeartRate
import io.github.lm98.whdt.core.hdt.model.property.Surname

fun main() {
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

    val hdts = listOf(
        HumanDigitalTwin(
            id = "hd1",
            models = listOf(model),
            physicalInterfaces = listOf(pI),
            digitalInterfaces = listOf(dI),
        )
    )

    val startedDts = WldtApp().addStartAll(hdts)
    println("Started Dts: ${startedDts.map { it.getOrNull() }}")
}