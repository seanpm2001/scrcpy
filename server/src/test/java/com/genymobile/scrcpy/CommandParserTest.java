package com.genymobile.scrcpy;

import com.genymobile.scrcpy.wrappers.DisplayManager;

import android.view.Display;

import org.junit.Assert;
import org.junit.Test;

public class CommandParserTest {
    @Test
    public void testParseDisplayInfoFromDumpsysDisplay() {
        /* @formatter:off */
        String partialOutput = "Logical Displays: size=1\n"
                + "  Display 0:\n"
                + "mDisplayId=0\n"
                + "    mLayerStack=0\n"
                + "    mHasContent=true\n"
                + "    mDesiredDisplayModeSpecs={baseModeId=2 primaryRefreshRateRange=[90 90] appRequestRefreshRateRange=[90 90]}\n"
                + "    mRequestedColorMode=0\n"
                + "    mDisplayOffset=(0, 0)\n"
                + "    mDisplayScalingDisabled=false\n"
                + "    mPrimaryDisplayDevice=Built-in Screen\n"
                + "    mBaseDisplayInfo=DisplayInfo{\"Built-in Screen\", displayId 0, FLAG_SECURE, FLAG_SUPPORTS_PROTECTED_BUFFERS, FLAG_TRUSTED, "
                + "real 1440 x 3120, largest app 1440 x 3120, smallest app 1440 x 3120, appVsyncOff 2000000, presDeadline 11111111, mode 2, "
                + "defaultMode 1, modes [{id=1, width=1440, height=3120, fps=60.0}, {id=2, width=1440, height=3120, fps=90.0}, {id=3, width=1080, "
                + "height=2340, fps=90.0}, {id=4, width=1080, height=2340, fps=60.0}], hdrCapabilities HdrCapabilities{mSupportedHdrTypes=[2, 3, 4], "
                + "mMaxLuminance=540.0, mMaxAverageLuminance=270.1, mMinLuminance=0.2}, minimalPostProcessingSupported false, rotation 0, state OFF, "
                + "type INTERNAL, uniqueId \"local:0\", app 1440 x 3120, density 600 (515.154 x 514.597) dpi, layerStack 0, colorMode 0, "
                + "supportedColorModes [0, 7, 9], address {port=129, model=0}, deviceProductInfo DeviceProductInfo{name=, manufacturerPnpId=QCM, "
                + "productId=1, modelYear=null, manufactureDate=ManufactureDate{week=27, year=2006}, relativeAddress=null}, removeMode 0}\n"
                + "    mOverrideDisplayInfo=DisplayInfo{\"Built-in Screen\", displayId 0, FLAG_SECURE, FLAG_SUPPORTS_PROTECTED_BUFFERS, "
                + "FLAG_TRUSTED, real 1440 x 3120, largest app 3120 x 2983, smallest app 1440 x 1303, appVsyncOff 2000000, presDeadline 11111111, "
                + "mode 2, defaultMode 1, modes [{id=1, width=1440, height=3120, fps=60.0}, {id=2, width=1440, height=3120, fps=90.0}, {id=3, "
                + "width=1080, height=2340, fps=90.0}, {id=4, width=1080, height=2340, fps=60.0}], hdrCapabilities "
                + "HdrCapabilities{mSupportedHdrTypes=[2, 3, 4], mMaxLuminance=540.0, mMaxAverageLuminance=270.1, mMinLuminance=0.2}, "
                + "minimalPostProcessingSupported false, rotation 0, state ON, type INTERNAL, uniqueId \"local:0\", app 1440 x 3120, density 600 "
                + "(515.154 x 514.597) dpi, layerStack 0, colorMode 0, supportedColorModes [0, 7, 9], address {port=129, model=0}, deviceProductInfo "
                + "DeviceProductInfo{name=, manufacturerPnpId=QCM, productId=1, modelYear=null, manufactureDate=ManufactureDate{week=27, year=2006}, "
                + "relativeAddress=null}, removeMode 0}\n"
                + "    mRequestedMinimalPostProcessing=false\n";
        DisplayInfo displayInfo = DisplayManager.parseDisplayInfo(partialOutput, 0);
        Assert.assertNotNull(displayInfo);
        Assert.assertEquals(0, displayInfo.getDisplayId());
        Assert.assertEquals(0, displayInfo.getRotation());
        Assert.assertEquals(0, displayInfo.getLayerStack());
        // FLAG_TRUSTED does not exist in Display (@TestApi), so it won't be reported
        Assert.assertEquals(Display.FLAG_SECURE | Display.FLAG_SUPPORTS_PROTECTED_BUFFERS, displayInfo.getFlags());
        Assert.assertEquals(1440, displayInfo.getSize().getWidth());
        Assert.assertEquals(3120, displayInfo.getSize().getHeight());
    }
}