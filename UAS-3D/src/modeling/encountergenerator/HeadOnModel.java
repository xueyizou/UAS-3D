package modeling.encountergenerator;

import modeling.SAAModel;
import configuration.Configuration;
import configuration.IntruderConfig;

public class HeadOnModel
{
	public static IntruderConfig newHeadOn(Configuration config)
	{
		int numHeadOn=config.intrudersConfig.size()+1;
		int sign=(numHeadOn%2 ==0)?-1:+1;
		int scale=numHeadOn/2;
			
		IntruderConfig headOnConfig = new IntruderConfig();
//		headOnConfig.intruderOffsetX = config.globalConfig.alertTime*(config.ownshipConfig.ownshipVx-headOnConfig.intruderGs);
		headOnConfig.intruderOffsetY = sign*scale*200;
//		headOnConfig.intruderOffsetZ = 2*(SAAModel.getExistingInstance().random.nextDouble()-0.5)*5000;
		return headOnConfig;
	}

}
