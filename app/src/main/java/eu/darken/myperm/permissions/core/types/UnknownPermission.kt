package eu.darken.myperm.permissions.core.types

import eu.darken.myperm.apps.core.features.ApkPkg
import eu.darken.myperm.apps.core.features.requestsPermission
import eu.darken.myperm.permissions.core.Permission

data class UnknownPermission(
    override val id: Permission.Id,
    override val requestingPkgs: List<ApkPkg> = emptyList(),
) : BasePermission() {

    override val grantingPkgs: Collection<ApkPkg> by lazy {
        requestingPkgs
            .filter { it.requestsPermission(this) }
            .filter { it.getPermission(id)?.isGranted == true }
    }

    override val declaringPkgs: Collection<ApkPkg>
        get() = emptyList()

    override fun toString(): String = "NormalPermission($id)"
}