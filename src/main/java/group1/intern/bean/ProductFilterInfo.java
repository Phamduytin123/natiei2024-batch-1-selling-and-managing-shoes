package group1.intern.bean;

import group1.intern.model.Embeddables.ProductDescription;
import group1.intern.model.ProductImage;
import group1.intern.model.ProductQuantity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductFilterInfo {
    private Integer id;
    private Integer productId;
    private String name;
    private Integer discount;
    private String gender;
    private ProductDescription description;
    private String category;
    private String style;
    private String material;
    private Integer price;
    private String color;
    private List<ProductImage> images;
    private List<ProductQuantity> sizeQuantity;
}