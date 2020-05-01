package nl.andrewlalis.irocapi.dto.rolling_stock;

import lombok.Getter;
import nl.andrewlalis.irocapi.model.rolling_stock.RollingStock;
import nl.andrewlalis.irocapi.model.rolling_stock.Status;

@Getter
public class RollingStockResponseWithLastStatus extends RollingStockResponse {
	private StatusResponse lastStatus;
	public RollingStockResponseWithLastStatus(RollingStock rollingStock, Status lastStatus) {
		super(rollingStock);
		this.lastStatus = new StatusResponse(lastStatus);
	}
}
