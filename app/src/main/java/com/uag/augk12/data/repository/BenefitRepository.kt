package com.uag.augk12.data.repository

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import com.uag.augk12.data.models.BenefitChildModel
import com.uag.augk12.data.models.BenefitModel

class BenefitRepository {
    fun genBenefit(): List<BenefitModel> {
        return listOf(
            BenefitModel(
                "Hoteles",
                "HTL",
                icon = Icons.Filled.Home,
                children = listOf(
                    BenefitChildModel("Real de Chapala", "RDC", icon = Icons.Filled.Home),
                    BenefitChildModel("Cabo Blanco", "CB", icon = Icons.Filled.Home),
                    BenefitChildModel("Real del Sol", "RDS", icon = Icons.Filled.Home),
                    BenefitChildModel("Isla Nandad", "IND", icon = Icons.Filled.Home),
                )
            ),
            BenefitModel("Alimentos", "ALM", icon = Icons.Filled.Home),
            BenefitModel("Servicios", "SVC", icon = Icons.Filled.Home),
            BenefitModel("Estilo de vida", "ESVA", icon = Icons.Filled.Home),
            BenefitModel("Entretenimiento", "ENT", icon = Icons.Filled.Home),
            BenefitModel("Salud y cuidado personal", "SCPS", icon = Icons.Filled.Home),
            BenefitModel("Ocacionales", "OCSL", icon = Icons.Filled.Home),
            BenefitModel("Opticas", "OTC", icon = Icons.Filled.Home)
        )
    }
}