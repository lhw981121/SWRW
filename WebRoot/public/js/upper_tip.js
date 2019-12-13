//大写提示

(function ($) {
    $.fn.extend({
        capsLockTip: function (divTipID) {
            return this.each(function () {
                //创建对象实例并保存。
                //获取实例对象：var api = $("#txtPWD").data("txtPWD");
                var ins = new $.CapsLockTip($(this));
                $(this).data(this.id, ins);
            });
        }
    });


    //创建一个实例。
    //___target jq目标对象。
    //___divTipID   显示提示文本的div。
    $.CapsLockTip = function (___target) {
        //设置当前实例的配置参数。
        this.target = ___target;
        var _this = this;
        $(document).ready(function () {
            //创建显示大写锁定的div。
            if(null == $.fn.capsLockTip.divTip){
                $("body").append("<div id='divTip__985124855558842555' style='width:100px; height:18px; line-height:18px;display:none; position:absolute; " +
								"z-index:9999999999999; text-align:center; background-color:#FDF6AA; color:Red; font-size:12px; border:solid 1px #DBC492; " +
								"border-bottom-color:#B49366; border-right-color:#B49366;'>"+(language=='zh_CN'?'大写锁定已打开':'Caps lock open')+"</div>");
                $.fn.capsLockTip.divTip = $("#divTip__985124855558842555");
            }
            _this.target.bind("keypress", function (_event) {
                var e = _event || window.event;
                var kc = e.keyCode || e.which;
                var isShift = e.shiftKey || (kc == 16) || false;
                $.fn.capsLockTip.capsLockActived = false;
                if ((kc >= 65 && kc <= 90 && !isShift) || (kc >= 97 && kc <= 122 && isShift))
                    $.fn.capsLockTip.capsLockActived = true;
                _this.showTips($.fn.capsLockTip.capsLockActived);
            });
            _this.target.bind("keydown", function (_event) {
                var e = _event || window.event;
                var kc = e.keyCode || e.which;
                if (kc == 20 && null != $.fn.capsLockTip.capsLockActived){
                    $.fn.capsLockTip.capsLockActived = !$.fn.capsLockTip.capsLockActived;
                    _this.showTips($.fn.capsLockTip.capsLockActived);
                }
            });
            _this.target.bind("focus", function (_event) {
                if (null != $.fn.capsLockTip.capsLockActived)
                    _this.showTips($.fn.capsLockTip.capsLockActived);
            });
            _this.target.bind("blur", function (_event) {
                _this.showTips(false);
            });
        });
        //显示或隐藏大写锁定提示。
        this.showTips = function (display) {
            if (display) {
                var offset = _this.target.offset();
                $.fn.capsLockTip.divTip.css("left", offset.left + "px");
                $.fn.capsLockTip.divTip.css("top", offset.top + _this.target[0].offsetHeight + 3 + "px");
                $.fn.capsLockTip.divTip.show();
            }
            else {
                $.fn.capsLockTip.divTip.hide();
            }
        };
        //jq控件公用静态对象。
        //提示框。
        $.fn.capsLockTip.divTip = null;
        //大写锁定键状态
        $.fn.capsLockTip.capsLockActived = null;
    };
})(jQuery);

$("#user_password").capsLockTip();
$("#user_passwords").capsLockTip();