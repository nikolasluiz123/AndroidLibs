package br.com.android.room.toolkit.model.health.enums

import android.content.Context
import br.com.android.room.toolkit.R
import br.com.android.room.toolkit.model.interfaces.IEnumDomain

enum class EnumRecordingMethod : IEnumDomain {
    RECORDING_METHOD_UNKNOWN {
        override fun getLabel(context: Context): String {
            return context.getString(R.string.enum_recording_method_unknown)
        }
    },
    RECORDING_METHOD_ACTIVELY_RECORDED {
        override fun getLabel(context: Context): String {
            return context.getString(R.string.enum_recording_method_active)
        }
    },
    RECORDING_METHOD_AUTOMATICALLY_RECORDED {
        override fun getLabel(context: Context): String {
            return context.getString(R.string.enum_recording_method_automatically)
        }
    },
    RECORDING_METHOD_MANUAL_ENTRY {
        override fun getLabel(context: Context): String {
            return context.getString(R.string.enum_recording_method_manual)
        }
    }
}