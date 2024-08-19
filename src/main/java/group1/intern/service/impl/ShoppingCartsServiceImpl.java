package group1.intern.service.impl;

import group1.intern.bean.ShoppingCartInfo;
import group1.intern.model.ShoppingCart;
import group1.intern.repository.customization.ShoppingCartsCustomRepository;
import group1.intern.repository.customization.impl.ShoppingCartsCustomRepositoryImpl;
import group1.intern.service.ShoppingCartsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShoppingCartsServiceImpl implements ShoppingCartsService {

    @Autowired
    private ShoppingCartsCustomRepository repository;
    @Autowired
    private ShoppingCartsCustomRepositoryImpl shoppingCartsCustomRepositoryImpl;

    @Override
    public List<ShoppingCartInfo> getShoppingCartsByCustomerId(Integer customerId) {
        List<ShoppingCart> shoppingCarts = repository.findAllByAccountId(customerId);


        return shoppingCarts.stream().map(shoppingCart -> ShoppingCartInfo.builder()
                .id(shoppingCart.getId())
                .customerId(shoppingCart.getAccount().getId())
                .productId(shoppingCart.getProductQuantity().getProductDetail().getProduct().getId())
                .productDetailId(shoppingCart.getProductQuantity().getProductDetail().getId())
                .quantity(shoppingCart.getQuantity())
                .style(shoppingCart.getProductQuantity().getProductDetail().getStyle().getValue())
                .priceProduct(shoppingCart.getProductQuantity().getProductDetail().getPrice())
                .price(shoppingCart.getQuantity() * shoppingCart.getProductQuantity().getProductDetail().getPrice())
                .size(shoppingCart.getProductQuantity().getSize().getValue())
                .name(shoppingCart.getProductQuantity().getProductDetail().getProduct().getName())
                .discount(shoppingCart.getProductQuantity().getProductDetail().getDiscount())
                .images(shoppingCart.getProductQuantity().getProductDetail().getImages())
                .sizeQuantity(shoppingCart.getProductQuantity())
                .build()
            )
            .collect(Collectors.toList());
    }

    @Override
    public Page<ShoppingCartInfo> getShoppingCartsByCustomerId(Integer customerId, Pageable pageable) {
        var shoppingCarts = repository.findAllByAccountId(customerId, pageable);
        return shoppingCarts.map(ShoppingCartInfo::fromShoppingCart);
    }
}